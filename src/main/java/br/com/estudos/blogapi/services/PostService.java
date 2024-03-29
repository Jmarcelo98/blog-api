package br.com.estudos.blogapi.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.ForbiddenException;
import br.com.estudos.blogapi.handlers.ResourceNotFoundException;
import br.com.estudos.blogapi.mappers.PostMapper;
import br.com.estudos.blogapi.model.dtos.PostDTO;
import br.com.estudos.blogapi.model.dtos.input.PostInputDTO;
import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	private final UserService userService;

	private final CategoryService categoryService;

	@Transactional
	public Integer create(PostInputDTO postInputDTO) {

		var userLogged = userService.getUserLogged();

		var category = categoryService.findById(postInputDTO.getCategory().getId());

		var post = Post.builder().id(null).createdAt(LocalDate.now()).updatedAt(null)
				.thumbnail(postInputDTO.getThumbnail()).description(postInputDTO.getDescription()).category(category)
				.title(postInputDTO.getTitle()).isPublished(postInputDTO.getIsPublished())
				.publishedAt(publishNow(postInputDTO.getIsPublished())).user(userLogged)
				.content(postInputDTO.getContent()).build();

		postRepository.save(post);

		log.info("Post cadastrado com sucesso");

		return post.getId();

	}

	@Transactional
	public void update(PostDTO postDTO) {

		var user = userService.getUserLogged();

		if (!itYourPost(postDTO.getId(), user.getId())) {
			throw new ForbiddenException("Impossível editar um POST que não é seu");
		}

		var post = postRepository.findById(postDTO.getId()).get();

		BeanUtils.copyProperties(postDTO, post);

		postRepository.save(post);

		log.info("Post atualizado com sucesso");

	}

	@Transactional
	public void publish(Integer id) {

		var user = userService.getUserLogged();

		if (!itYourPost(id, user.getId())) {
			throw new ForbiddenException("Impossível publicar um POST que não é seu");
		}

		var post = postRepository.findById(id);

		post.get().setPublishedAt(LocalDate.now());
		post.get().setIsPublished(true);
		postRepository.save(post.get());
	}

	@Transactional
	public void delete(Integer idPost) {

		var user = userService.getUserLogged();

		if (!itYourPost(idPost, user.getId())) {
			throw new ForbiddenException("Impossível apagar um POST que não é seu");
		}

		postRepository.deleteById(idPost);
		log.info("Post deletado com sucesso");
	}

	public List<PostDTO> getMostRecentPost() {

		PageRequest pageRequest = PageRequest.of(0, 4);

		var list = postRepository.findAllByIsPublishedTrueOrderByPublishedAtDesc(pageRequest);

		return PostMapper.INSTANCE.listaEntityToListaDTO(list);

	}

	public Post findById(Integer id) {
		return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
	}

	public PostDTO findByIdPostPublished(Integer id) {
		var postById = postRepository.findByIdAndIsPublishedTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
		return PostMapper.INSTANCE.entityToDTO(postById);
	}

	public Integer countPostsCreated(String nickname) {
		var user = userService.findByNickname(nickname);
		return postRepository.countByUserAndIsPublishedTrue(user);
	}

	public Boolean existsPostLock() {
		var user = userService.getUserLogged();
		return postRepository.existsByUserAndIsPublishedFalse(user);
	}

	public List<PostDTO> findAllByUser(String nickname, Integer page, Integer itensPerPage) {

		var user = userService.getUserLogged();

		PageRequest pageRequest = PageRequest.of(page, itensPerPage);

		var listPost = postRepository.findAllByUserAndIsPublishedTrueOrderByPublishedAtDesc(user, pageRequest);

		return PostMapper.INSTANCE.listaEntityToListaDTO(listPost);

	}

	public List<PostDTO> findAllByUserNotPublished(Integer page, Integer itensPerPage) {

		var user = userService.getUserLogged();

		PageRequest pageRequest = PageRequest.of(page, itensPerPage);

		var listPost = postRepository.findAllByUserAndIsPublishedFalseOrderByCreatedAtDesc(user, pageRequest);

		return PostMapper.INSTANCE.listaEntityToListaDTO(listPost);

	}

	private Boolean itYourPost(Integer idPost, Integer idUsuario) {

		return postRepository.itYourPost(idPost, idUsuario);

	}

	private LocalDate publishNow(Boolean isPublished) {
		if (isPublished) {
			return LocalDate.now();
		}
		return null;
	}

}
