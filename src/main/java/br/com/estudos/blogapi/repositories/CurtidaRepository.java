package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Curtida;

public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {

}
