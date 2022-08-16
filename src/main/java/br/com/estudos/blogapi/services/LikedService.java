package br.com.estudos.blogapi.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Liked;
import br.com.estudos.blogapi.repositories.LikedRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class LikedService {

	private final PostService postService;

	private final UserService userService;

	private final LikedRepository likedRepository;

	@Transactional
	public void create(Integer idPost) {

		var post = postService.findById(idPost);
		var user = userService.getUserLogged();

		var like = Liked.builder().id(null).createdAt(LocalDate.now()).post(post).user(user).build();
		likedRepository.save(like);
		log.info("Criado liked");
	}

	@Transactional
	public void delete(Integer idPost) {
		var post = postService.findById(idPost);
		var user = userService.getUserLogged();

		likedRepository.deleteByPostAndUser(post, user);

	}

	public Integer countLikedByIdPost(Integer idPost) {

		var post = postService.findById(idPost);

		return likedRepository.countByPost(post);
	}

	public Boolean isLikedByIdPost(Integer idPost) {

		var post = postService.findById(idPost);

		var user = userService.getUserLogged();

		return likedRepository.existsByPostAndUser(post, user);
	}

}
