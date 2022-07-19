package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

}
