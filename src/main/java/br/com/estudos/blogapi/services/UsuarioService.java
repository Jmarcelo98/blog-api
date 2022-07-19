package br.com.estudos.blogapi.services;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public void deletar(Integer id) {
		usuarioRepository.deleteById(id);
	}

}
