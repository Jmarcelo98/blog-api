package br.com.estudos.blogapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
