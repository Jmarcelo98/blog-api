package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.services.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;

	@DeleteMapping
	public ResponseEntity<Void> deletar(Integer id) {
		usuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}

}
