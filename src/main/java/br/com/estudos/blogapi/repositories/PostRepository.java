package br.com.estudos.blogapi.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.Usuario;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUsuarioAndIsPublicadoTrueOrderByCriadoEm(Usuario usuario, Pageable pageRequest);

}
