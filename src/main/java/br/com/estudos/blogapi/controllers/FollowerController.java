package br.com.estudos.blogapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.dtos.output.UserOutputDTO;
import br.com.estudos.blogapi.services.FollowerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/follower")
@AllArgsConstructor
public class FollowerController {

	private final FollowerService followerService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody String nickname) {
		followerService.create(nickname);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{nickname}")
	public ResponseEntity<Void> delete(@PathVariable("nickname") String nickname) {
		followerService.delete(nickname);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/most-followers")
	public ResponseEntity<List<UserOutputDTO>> getMostFollowers() {
		return ResponseEntity.ok(followerService.getMostFollowers());
	}

	@GetMapping(path = "/all-followers/{nickname}")
	public ResponseEntity<List<UserOutputDTO>> findAllFollowers(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.findAllFollowers(nickname));
	}

	@GetMapping(path = "/all-following/{nickname}")
	public ResponseEntity<List<UserOutputDTO>> findAllFollowing(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.findAllFollowing(nickname));
	}

	@GetMapping(path = "/isFollow/{nickname}")
	public ResponseEntity<Boolean> isFollow(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.isFollow(nickname));
	}

	@GetMapping(value = "/count-follower/{nickname}")
	public ResponseEntity<Map<String, Integer>> countFollower(@PathVariable("nickname") String nickname) {
		return ResponseEntity.ok(followerService.countFollower(nickname));
	}

}
