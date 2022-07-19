package br.com.estudos.blogapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Categoria;
import br.com.estudos.blogapi.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public List<Categoria> buscarTodasCategorias() {
		return categoriaRepository.findAll();
	}

}
