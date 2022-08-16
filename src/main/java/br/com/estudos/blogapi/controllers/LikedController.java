package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.services.LikedService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/liked")
@AllArgsConstructor
public class LikedController {

	private final LikedService likedService;

	@GetMapping(path = "/count/{id}")
	public ResponseEntity<Integer> countLikedByIdPost(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(likedService.countLikedByIdPost(id));
	}

	@GetMapping(path = "/isLiked/{id}")
	public ResponseEntity<Boolean> isLikedByIdPost(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(likedService.isLikedByIdPost(id));
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Integer id) {
		likedService.create(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		likedService.delete(id);
		return ResponseEntity.ok().build();
	}

}
