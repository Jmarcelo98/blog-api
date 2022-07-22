package br.com.estudos.blogapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.blogapi.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByNicknameIgnoreCase(String nickname);

	Boolean existsByNicknameIgnoreCase(String nickname);

	void deleteByNickname(String nickname);

}
