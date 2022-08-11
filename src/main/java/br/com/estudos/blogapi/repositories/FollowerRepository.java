package br.com.estudos.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estudos.blogapi.model.entities.Follower;
import br.com.estudos.blogapi.model.entities.User;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {

	List<Follower> findAllByFollowOrderByCreatedAtDesc(User user);

	List<Follower> findAllByFollowedOrderByCreatedAtDesc(User user);

	@Query(value = "SELECT f.ID_USER_FOLLOWED FROM FOLLOWER f GROUP BY ID_USER_FOLLOWED ORDER BY COUNT(*) DESC LIMIT 4", nativeQuery = true)
	List<Integer> getMostFollowers();

	Integer countByFollow(User user);

	Integer countByFollowed(User user);

	Boolean existsByFollowAndFollowed(User from, User to);

	Follower findByFollowAndFollowed(User segue, User seguido);

}
