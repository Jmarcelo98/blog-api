package br.com.estudos.blogapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.NegocioException;
import br.com.estudos.blogapi.mappers.output.SeguidoresOutputMapper;
import br.com.estudos.blogapi.mappers.output.SeguindoOutputMapper;
import br.com.estudos.blogapi.model.dtos.output.SeguidoresOutputDTO;
import br.com.estudos.blogapi.model.dtos.output.SeguindoOutputDTO;
import br.com.estudos.blogapi.model.entities.Seguidor;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.SeguidorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeguidorService {

	private final SeguidorRepository seguidorRepository;

	private final UsuarioService usuarioService;

	@Transactional
	public void deletar(Integer idLogado, Integer idASeguir) {

		var usuarioLogado = buscarUsuarioPorId(idLogado);
		var usuarioASerSeguido = buscarUsuarioPorId(idASeguir);

		if (!podeSeguir(usuarioLogado, usuarioASerSeguido)) {
			throw new NegocioException("Você não pode deixar de seguir a você mesmo");
		}

		var seguidor = seguidorRepository.findBySegueAndSeguido(usuarioLogado, usuarioASerSeguido);
		
		if (seguidor == null) {
			throw new NegocioException("Você não ainda não segue esse usuário");
		}
		
		seguidorRepository.deleteById(seguidor.getId());

	}

	@Transactional
	public void inserir(Integer idLogado, Integer idASeguir) {

		var usuarioLogado = buscarUsuarioPorId(idLogado);
		var usuarioASerSeguido = buscarUsuarioPorId(idASeguir);

		if (!podeSeguir(usuarioLogado, usuarioASerSeguido)) {
			throw new NegocioException("Você não pode seguir a você mesmo");
		}

		if (jaSegue(usuarioLogado, usuarioASerSeguido)) {
			throw new NegocioException("O " + usuarioLogado.getNome() + " já segue o " + usuarioASerSeguido.getNome());
		}

		var seguidor = Seguidor.builder().id(null).segue(usuarioLogado).seguido(usuarioASerSeguido).build();

		seguidorRepository.save(seguidor);

	}

	public List<SeguidoresOutputDTO> buscarSeguidores(Integer id) {

		var usuario = buscarUsuarioPorId(id);
		var seguidores = seguidorRepository.findAllBySeguido(usuario);

		return SeguidoresOutputMapper.INSTANCE.listaEntityToListaDTO(seguidores);

	}

	public List<SeguindoOutputDTO> buscarSeguindo(Integer id) {
		var usuario = buscarUsuarioPorId(id);
		var seguindo = seguidorRepository.findAllBySegue(usuario);
		return SeguindoOutputMapper.INSTANCE.listaEntityToListaDTO(seguindo);
	}

	public Map<String, Integer> buscarQuantidadeSeguidor(Integer id) {
		var usuario = buscarUsuarioPorId(id);

		var seguindo = seguidorRepository.countBySegue(usuario);
		var seguidores = seguidorRepository.countBySeguido(usuario);

		Map<String, Integer> quantidadeSeguidor = new HashMap<>();

		quantidadeSeguidor.put("seguindo", seguindo);
		quantidadeSeguidor.put("seguidores", seguidores);
		return quantidadeSeguidor;
	}

	public Usuario buscarUsuarioPorId(Integer id) {
		return usuarioService.buscarPorId(id);
	}

	private boolean jaSegue(Usuario usuarioLogado, Usuario aSeguir) {
		return seguidorRepository.existsBySegueAndSeguido(usuarioLogado, aSeguir);
	}

	private boolean podeSeguir(Usuario usuarioLogado, Usuario aSeguir) {

		if (usuarioLogado == aSeguir) {
			return false;
		}

		return true;

	}

}
