package br.com.estudos.blogapi.services;

import org.springframework.stereotype.Service;

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
