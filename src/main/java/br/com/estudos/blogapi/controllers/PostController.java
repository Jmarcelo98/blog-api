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

import br.com.estudos.blogapi.configs.security.JWTUtils;
import br.com.estudos.blogapi.model.dtos.PostDTO;
import br.com.estudos.blogapi.model.dtos.input.PostInputDTO;
import br.com.estudos.blogapi.services.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	private final JWTUtils jwtUtils;

	@PostMapping
	public ResponseEntity<Integer> create(@RequestBody PostInputDTO postInputDTO) {
		return ResponseEntity.ok(postService.create(postInputDTO, jwtUtils.getPrincipal()));
	}

	@PatchMapping
	public ResponseEntity<Void> update(@RequestBody PostDTO postDTO) {
		postService.update(postDTO, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		postService.delete(id, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/most-recent")
	public ResponseEntity<List<PostDTO>> getMostRecentPost() {
		return ResponseEntity.ok(postService.getMostRecentPost());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PostDTO> findByIdPostDTO(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(postService.findByIdPostDTO(id));
	}

	@GetMapping(path = "/all/{nickname}")
	public ResponseEntity<List<PostDTO>> findAllByUser(@PathVariable("nickname") String nickname,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "itensPerPage", defaultValue = "10") Integer itensPerPage) {
		return ResponseEntity.ok(postService.findAllByUser(nickname, page, itensPerPage));
	}

	@GetMapping(path = "/count/{nickname}")
	public ResponseEntity<Integer> countPostsCreated(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(postService.countPostsCreated(nickname));
	}

}
