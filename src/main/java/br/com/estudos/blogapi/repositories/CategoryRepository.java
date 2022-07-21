package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
