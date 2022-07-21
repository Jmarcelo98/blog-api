package br.com.estudos.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.Follower;
import br.com.estudos.blogapi.model.entities.User;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {

	List<Follower> findAllByFollow(User user);

	List<Follower> findAllByFollowed(User user);

	Integer countByFollow(User user);

	Integer countByFollowed(User user);

	Boolean existsByFollowAndFollowed(User from, User to);

	Follower findByFollowAndFollowed(User segue, User seguido);

}
