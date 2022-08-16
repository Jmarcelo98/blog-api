package br.com.estudos.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Liked;
import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.User;

public interface LikedRepository extends JpaRepository<Liked, Integer> {

	Integer countByPost(Post post);

	Boolean existsByPostAndUser(Post post, User user);

}
