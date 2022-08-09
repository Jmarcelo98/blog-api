package br.com.estudos.blogapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.ResourceNotFoundException;
import br.com.estudos.blogapi.model.entities.Category;
import br.com.estudos.blogapi.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada através do ID"));
	}

}
