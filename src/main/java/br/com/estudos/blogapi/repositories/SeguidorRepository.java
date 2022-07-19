package br.com.estudos.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Seguidor;
import br.com.estudos.blogapi.model.entities.Usuario;

public interface SeguidorRepository extends JpaRepository<Seguidor, Integer> {

	List<Seguidor> findAllBySegue(Usuario usuario);

	List<Seguidor> findAllBySeguido(Usuario usuario);

	Integer countBySegue(Usuario usuario);

	Integer countBySeguido(Usuario usuario);

	Boolean existsBySegueAndSeguido(Usuario segue, Usuario seguido);

	Seguidor findBySegueAndSeguido(Usuario segue, Usuario seguido);

}
