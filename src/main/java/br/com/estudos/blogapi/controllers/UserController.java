package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.configs.security.JWTUtils;
import br.com.estudos.blogapi.model.dtos.UserDTO;
import br.com.estudos.blogapi.model.dtos.input.UserRegistrationInputDTO;
import br.com.estudos.blogapi.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	private final JWTUtils jwtUtils;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UserRegistrationInputDTO userRegistrationInputDTO) {
		userService.create(userRegistrationInputDTO);
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) {
		userService.update(userDTO, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}

}
