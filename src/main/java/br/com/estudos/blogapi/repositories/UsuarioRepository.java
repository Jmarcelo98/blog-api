package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
