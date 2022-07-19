package br.com.estudos.blogapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.dtos.output.SeguidoresOutputDTO;
import br.com.estudos.blogapi.model.dtos.output.SeguindoOutputDTO;
import br.com.estudos.blogapi.services.SeguidorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguidor")
@AllArgsConstructor
public class SeguidorController {

	private final SeguidorService seguidorService;

	@PostMapping
	public ResponseEntity<Void> inserir(Integer idLogado, Integer idASeguir) {
		seguidorService.inserir(idLogado, idASeguir);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deletar(Integer idLogado, Integer idASeguir) {
		seguidorService.deletar(idLogado, idASeguir);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/todos-seguidores")
	public ResponseEntity<List<SeguidoresOutputDTO>> buscarSeguidores(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarSeguidores(id));
	}

	@GetMapping(value = "/todos-seguidos")
	public ResponseEntity<List<SeguindoOutputDTO>> buscarSeguidos(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarSeguindo(id));
	}

	@GetMapping(value = "/quantidade-seguidor")
	public ResponseEntity<Map<String, Integer>> buscarQuantidadeSeguidor(Integer id) {
		return ResponseEntity.ok(seguidorService.buscarQuantidadeSeguidor(id));
	}

}
