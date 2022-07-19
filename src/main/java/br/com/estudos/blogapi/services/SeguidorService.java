package br.com.estudos.blogapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Seguidor;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.SeguidorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeguidorService {

	private final SeguidorRepository seguidorRepository;

	private final UsuarioService usuarioService;

	public List<Seguidor> buscarSeguidores(Integer id) {
		var usuario = buscarUsuarioPorId(id);
		return seguidorRepository.findAllBySeguido(usuario);
	}

	public List<Seguidor> buscarSeguindo(Integer id) {
		var usuario = buscarUsuarioPorId(id);
		return seguidorRepository.findAllBySegue(usuario);
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

}
