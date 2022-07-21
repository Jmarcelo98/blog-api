package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Liked;

public interface LikedRepository extends JpaRepository<Liked, Integer> {

}
