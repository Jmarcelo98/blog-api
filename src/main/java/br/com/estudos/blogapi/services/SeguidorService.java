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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class SeguidorService {

	private final SeguidorRepository seguidorRepository;

	private final UsuarioService usuarioService;

	@Transactional
	public void deletar(String apelidoADesseguir, String apelidoLogado) {

		if (isMesmoUsuario(apelidoADesseguir, apelidoLogado)) {
			throw new NegocioException("Você não pode desseguir a você mesmo");
		}

		var usuarioLogado = buscarUsuarioPorApelido(apelidoLogado);

		var usuarioASerDesseguido = buscarUsuarioPorApelido(apelidoADesseguir);

		if (!jaSegue(usuarioLogado, usuarioASerDesseguido)) {
			log.error("O " + usuarioLogado.getNome() + " não segue o " + usuarioASerDesseguido.getNome());
			throw new NegocioException("Você não segue este usuário para poder desseguir");
		}

		var seguidor = seguidorRepository.findBySegueAndSeguido(usuarioLogado, usuarioASerDesseguido);

//		if (seguidor == null) {
//			log.error("Você não pode parar de seguir um usuário que ainda não segue");
//			throw new NegocioException("Você não pode parar de seguir um usuário que ainda não segue");
//		}
//
		seguidorRepository.deleteById(seguidor.getId());

		log.info("Usuário desseguido com sucesso");

	}

	@Transactional
	public void inserir(String apelidoASeguir, String apelidoLogado) {

		if (isMesmoUsuario(apelidoASeguir, apelidoLogado)) {
			throw new NegocioException("Você não pode seguir a você mesmo");
		}

		var usuarioLogado = buscarUsuarioPorApelido(apelidoLogado);

		var usuarioASerSeguido = buscarUsuarioPorApelido(apelidoASeguir);

		if (jaSegue(usuarioLogado, usuarioASerSeguido)) {
			log.error("O " + usuarioLogado.getNome() + " já segue o " + usuarioASerSeguido.getNome());
			throw new NegocioException("Você já segue este usuário");
		}

		var seguidor = Seguidor.builder().id(null).segue(usuarioLogado).seguido(usuarioASerSeguido).build();
		seguidorRepository.save(seguidor);

		log.info("Usuário seguido com sucesso");

	}

	public List<SeguidoresOutputDTO> buscarSeguidores(String apelido) {
		var usuario = buscarUsuarioPorApelido(apelido);
		var seguidores = seguidorRepository.findAllBySeguido(usuario);
		return SeguidoresOutputMapper.INSTANCE.listaEntityToListaDTO(seguidores);

	}

	public List<SeguindoOutputDTO> buscarSeguindo(String apelido) {
		var usuario = buscarUsuarioPorApelido(apelido);
		var seguindo = seguidorRepository.findAllBySegue(usuario);
		return SeguindoOutputMapper.INSTANCE.listaEntityToListaDTO(seguindo);
	}

	public Map<String, Integer> buscarQuantidadeSeguidor(String apelido) {
		var usuario = buscarUsuarioPorApelido(apelido);

		var seguindo = seguidorRepository.countBySegue(usuario);
		var seguidores = seguidorRepository.countBySeguido(usuario);

		Map<String, Integer> quantidadeSeguidor = new HashMap<>();

		quantidadeSeguidor.put("seguindo", seguindo);
		quantidadeSeguidor.put("seguidores", seguidores);
		return quantidadeSeguidor;
	}

	private Usuario buscarUsuarioPorApelido(String apelido) {
		return usuarioService.buscarPorApelido(apelido);
	}

	private boolean jaSegue(Usuario usuarioLogado, Usuario aSeguir) {
		return seguidorRepository.existsBySegueAndSeguido(usuarioLogado, aSeguir);
	}

	private boolean isMesmoUsuario(String apelidoUsuario, String logado) {

		if (apelidoUsuario == logado) {
			return true;
		}

		return false;

	}

}
