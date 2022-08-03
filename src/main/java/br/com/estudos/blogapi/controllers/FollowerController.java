package br.com.estudos.blogapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.configs.security.JWTUtils;
import br.com.estudos.blogapi.model.dtos.output.UserOutputDTO;
import br.com.estudos.blogapi.services.FollowerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/follower")
@AllArgsConstructor
public class FollowerController {

	private final FollowerService followerService;

	private final JWTUtils jwtUtils;

	@PostMapping(path = "/{nickname}")
	public ResponseEntity<Void> create(@PathVariable("nickname") String nickname) {
		followerService.create(nickname, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{nickname}")
	public ResponseEntity<Void> delete(@PathVariable("nickname") String nickname) {
		followerService.delete(nickname, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/all-followers/{nickname}")
	public ResponseEntity<List<UserOutputDTO>> findAllFollowers(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.findAllFollowers(nickname));
	}

	@GetMapping(value = "/all-following/{nickname}")
	public ResponseEntity<List<UserOutputDTO>> findAllFollowing(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.findAllFollowing(nickname));
	}

	@GetMapping(value = "/count-follower/{nickname}")
	public ResponseEntity<Map<String, Integer>> countFollower(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.countFollower(nickname));
	}

}
