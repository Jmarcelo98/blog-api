package br.com.estudos.blogapi.services;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.RecursoNaoEncontradoException;
import br.com.estudos.blogapi.mappers.PostMapper;
import br.com.estudos.blogapi.model.dtos.PostDTO;
import br.com.estudos.blogapi.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	private final UsuarioService usuarioService;

	@Transactional
	public void inserir(PostDTO postDTO, Integer idLogado) {

		var usuario = usuarioService.buscarPorId(idLogado);

		var post = PostMapper.INSTANCE.DTOToEntity(postDTO);

		post.setUsuario(usuario);

		postRepository.save(post);

	}

	@Transactional
	public void atualizar(PostDTO postDTO) {

		var post = postRepository.findById(postDTO.getId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado através do ID"));

		BeanUtils.copyProperties(postDTO, post);

		postRepository.save(post);

	}

}
