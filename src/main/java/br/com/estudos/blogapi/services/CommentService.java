package br.com.estudos.blogapi.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.configs.security.JWTUtils;
import br.com.estudos.blogapi.model.dtos.input.CommentInputDTO;
import br.com.estudos.blogapi.model.entities.Comment;
import br.com.estudos.blogapi.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	private final UserService userService;

	private final JWTUtils jwtUtils;

	private final PostService postService;

	@Transactional
	public void create(CommentInputDTO commentInputDTO) {

		var user = userService.findByNickname(jwtUtils.getPrincipal());
		var post = postService.findById(commentInputDTO.getIdPost());

		var commentEntity = Comment.builder().id(null).comment(commentInputDTO.getComment()).createdAt(LocalDate.now())
				.user(user).post(post).build();

		commentRepository.save(commentEntity);

		log.info("Comentário criado");

	}

}
