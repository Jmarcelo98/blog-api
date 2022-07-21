package br.com.estudos.blogapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.ResourceNotFoundException;
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

	private final UserService userService;

	@Transactional
	public void create(PostDTO postDTO, String logged) {

		var userLogged = userService.findByNickname(logged);
		var post = PostMapper.INSTANCE.DTOToEntity(postDTO);
		post.setUser(userLogged);
		postRepository.save(post);

		log.info("Post cadastrado com sucesso");

	}

	@Transactional
	public void update(PostDTO postDTO) {

		var post = postRepository.findById(postDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Post não encontrado através do ID"));

		BeanUtils.copyProperties(postDTO, post);

		postRepository.save(post);

		log.info("Post atualizado com sucesso");

	}

	@Transactional
	public void delete(Integer idPost) {
		postRepository.deleteById(idPost);
		log.info("Post deletado com sucesso");
	}

	public List<PostDTO> findAllByUser(String nickname, Integer page, Integer itensPerPage) {

		var user = userService.findByNickname(nickname);

		PageRequest pageRequest = PageRequest.of(page, itensPerPage);

		var listPost = postRepository.findAllByUserAndIsPublishedTrueOrderByCreatedAt(user, pageRequest);

		return PostMapper.INSTANCE.listaEntityToListaDTO(listPost);

	}

}
