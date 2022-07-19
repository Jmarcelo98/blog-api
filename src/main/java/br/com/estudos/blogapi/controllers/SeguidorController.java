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

import br.com.estudos.blogapi.model.dtos.output.SeguidoresOutputDTO;
import br.com.estudos.blogapi.model.dtos.output.SeguindoOutputDTO;
import br.com.estudos.blogapi.services.SeguidorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguidor")
@AllArgsConstructor
public class SeguidorController {

	private final SeguidorService seguidorService;

	@PostMapping(path = "/{apelido}")
	public ResponseEntity<Void> inserir(@PathVariable("apelido") String apelido, Integer idLogado) {
		seguidorService.inserir(idLogado, apelido);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{apelido}")
	public ResponseEntity<Void> deletar(@PathVariable("apelido") String apelido, Integer idLogado) {
		seguidorService.deletar(idLogado, apelido);
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
