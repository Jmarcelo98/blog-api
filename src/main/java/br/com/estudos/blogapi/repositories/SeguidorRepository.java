package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Seguidor;

public interface SeguidorRepository extends JpaRepository<Seguidor, Integer> {

}
