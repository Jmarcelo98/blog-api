package br.com.estudos.blogapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
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

		log.info("Post cadastrado com sucesso");

	}

	@Transactional
	public void atualizar(PostDTO postDTO) {

		var post = postRepository.findById(postDTO.getId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Post não encontrado através do ID"));

		BeanUtils.copyProperties(postDTO, post);

		postRepository.save(post);
		
		log.info("Post atualizado com sucesso");

	}

	@Transactional
	public void deletar(Integer idPost) {
		postRepository.deleteById(idPost);
		log.info("Post deletado com sucesso");
	}

	public List<PostDTO> buscarPostsDoUsuario(String apelido, Integer pagina, Integer itensPorPagina) {

		var usuario = usuarioService.buscarPorApelido(apelido);

		PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina);

		var listaPost = postRepository.findAllByUsuarioAndIsPublicadoTrueOrderByCriadoEm(usuario, pageRequest);

		return PostMapper.INSTANCE.listaEntityToListaDTO(listaPost);

	}

}
