package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.dtos.input.CommentInputDTO;
import br.com.estudos.blogapi.services.CommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CommentInputDTO commentInputDTO) {
		commentService.create(commentInputDTO);
		return ResponseEntity.ok().build();
	}

}
