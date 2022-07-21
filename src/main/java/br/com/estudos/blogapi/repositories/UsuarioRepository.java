package br.com.estudos.blogapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByApelidoIgnoreCase(String apelido);

	Boolean existsByApelidoIgnoreCase(String apelido);

}
