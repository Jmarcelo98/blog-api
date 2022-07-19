package br.com.estudos.blogapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.dtos.UsuarioDTO;
import br.com.estudos.blogapi.services.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Void> inserir(String apelido, String senha) {
		usuarioService.inserir(apelido, senha);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deletar(Integer id) {
		usuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<Void> editar(@RequestBody UsuarioDTO usuarioDTO, Integer idLogado) {
		usuarioService.editar(usuarioDTO, idLogado);
		return ResponseEntity.ok().build();
	}

}
