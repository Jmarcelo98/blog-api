package br.com.estudos.blogapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(path = "/{apelido}")
	public ResponseEntity<List<PostDTO>> buscarPostsDoUsuario(@PathVariable("apelido") String apelido,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina) {
		return ResponseEntity.ok(postService.buscarPostsDoUsuario(apelido, pagina, itensPorPagina));
	}

}
