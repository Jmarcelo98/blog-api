package br.com.estudos.blogapi.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.RecursoNaoEncontradoException;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Transactional
	public void deletar(Integer id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario buscarPorId(Integer id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado através do ID"));
	}

	public Usuario buscarPorApelido(String apelido) {
		return usuarioRepository.findByApelido(apelido)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado através do apelido"));
	}

}
