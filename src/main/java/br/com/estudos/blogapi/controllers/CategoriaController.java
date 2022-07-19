package br.com.estudos.blogapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.blogapi.model.entities.Categoria;
import br.com.estudos.blogapi.services.CategoriaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

	private final CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> buscarTodasCategorias() {
		return ResponseEntity.ok(categoriaService.buscarTodasCategorias());
	}

}
