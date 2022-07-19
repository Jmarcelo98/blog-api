package br.com.estudos.blogapi.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.NegocioException;
import br.com.estudos.blogapi.handlers.RecursoNaoEncontradoException;
import br.com.estudos.blogapi.model.dtos.UsuarioDTO;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Transactional
	public void inserir(String apelido, String senha) {

		if (existePeloApelido(apelido)) {
			log.error("Apelido: " + apelido + " já existe");
			throw new NegocioException("Apelido já existe");
		}

		var usuario = Usuario.builder().apelido(apelido).isPremium(false).senha(senha).criadoEm(LocalDate.now())
				.build();

		usuarioRepository.save(usuario);

	}

	@Transactional
	public void atualizar(UsuarioDTO usuarioDTO, Integer idLogado) {
		var usuario = buscarPorId(idLogado);

		BeanUtils.copyProperties(usuarioDTO, usuario);

		usuarioRepository.save(usuario);

	}

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

	private Boolean existePeloApelido(String apelido) {
		return usuarioRepository.existsByApelido(apelido);
	}

}
