package br.com.estudos.blogapi.services;

import java.util.List;

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

	public Usuario buscarUsuarioPorId(Integer id) {
		return usuarioService.buscarPorId(id);
	}

}
