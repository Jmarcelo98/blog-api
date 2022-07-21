package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
