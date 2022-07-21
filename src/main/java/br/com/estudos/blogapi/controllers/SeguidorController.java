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
import br.com.estudos.blogapi.model.dtos.output.SeguidoresOutputDTO;
import br.com.estudos.blogapi.model.dtos.output.SeguindoOutputDTO;
import br.com.estudos.blogapi.services.SeguidorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguidor")
@AllArgsConstructor
public class SeguidorController {

	private final SeguidorService seguidorService;

	private final JWTUtils jwtUtils;

	@PostMapping(path = "/{apelido}")
	public ResponseEntity<Void> inserir(@PathVariable("apelido") String apelidoASeguir) {
		seguidorService.inserir(apelidoASeguir, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{apelido}")
	public ResponseEntity<Void> deletar(@PathVariable("apelido") String apelidoADesseguir) {
		seguidorService.deletar(apelidoADesseguir, jwtUtils.getPrincipal());
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/todos-seguidores/{apelido}")
	public ResponseEntity<List<SeguidoresOutputDTO>> buscarSeguidores(@PathVariable("apelido") String apelido) {
		return ResponseEntity.ok(seguidorService.buscarSeguidores(apelido));
	}

	@GetMapping(value = "/todos-seguidos/{apelido}")
	public ResponseEntity<List<SeguindoOutputDTO>> buscarSeguidos(@PathVariable("apelido") String apelido) {
		return ResponseEntity.ok(seguidorService.buscarSeguindo(apelido));
	}

	@GetMapping(value = "/total/{apelido}")
	public ResponseEntity<Map<String, Integer>> buscarQuantidadeSeguidor(@PathVariable("apelido") String apelido) {
		return ResponseEntity.ok(seguidorService.buscarQuantidadeSeguidor(apelido));
	}

}
