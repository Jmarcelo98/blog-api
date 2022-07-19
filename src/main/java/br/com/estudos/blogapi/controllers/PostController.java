package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.dtos.PostDTO;
import br.com.estudos.blogapi.services.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody PostDTO postDTO, Integer idLogado) {
		postService.inserir(postDTO, idLogado);
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<Void> atualizar(@RequestBody PostDTO postDTO) {
		postService.atualizar(postDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deletar(Integer idPost) {
		postService.deletar(idPost);
		return ResponseEntity.ok().build();
	}

}
