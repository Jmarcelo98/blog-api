package br.com.estudos.blogapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUserAndIsPublishedTrueOrderByPublishedAtDesc(User user, Pageable pageRequest);
	
	List<Post> findAllByUserAndIsPublishedFalseOrderByCreatedAtDesc(User user, Pageable pageRequest);

	@Query(value = "SELECT DISTINCT CASE WHEN EXISTS "
			+ "(SELECT * FROM POST p WHERE p.id = ?1 and p.id_user = ?2) then 'TRUE' else 'FALSE' end FROM post", nativeQuery = true)
	Boolean itYourPost(Integer idPost, Integer idLogado);

	Integer countByUserAndIsPublishedTrue(User user);
	
	Boolean existsByUserAndIsPublishedFalse(User user);
	
	Optional<Post> findByIdAndIsPublishedTrue(Integer id);
	
//	Optional<Post> findByIdAndIsPublishedFalseAndUser(Integer id, User user);
	
	List<Post> findAllByIsPublishedTrueOrderByPublishedAtDesc(Pageable pageRequest);

}
