package br.com.estudos.blogapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.entities.Seguidor;
import br.com.estudos.blogapi.services.SeguidorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguidor")
@AllArgsConstructor
public class SeguidorController {

	private final SeguidorService seguidorService;

	@GetMapping(value = "/todos-seguidores")
	public ResponseEntity<List<Seguidor>> buscarSeguidores(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarSeguidores(id));
	}

	@GetMapping(value = "/todos-seguidos")
	public ResponseEntity<List<Seguidor>> buscarSeguidos(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarSeguindo(id));
	}

	@GetMapping(value = "/quantidade-seguidor")
	public ResponseEntity<Map<String, Integer>> buscarQuantidadeSeguidor(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarQuantidadeSeguidor(id));
	}

}
