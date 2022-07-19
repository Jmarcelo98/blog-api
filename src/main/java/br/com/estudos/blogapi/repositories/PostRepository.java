package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
