package br.com.estudos.blogapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.NegocioException;
import br.com.estudos.blogapi.mappers.output.FollowersOutputMapper;
import br.com.estudos.blogapi.mappers.output.FollowingOutputMapper;
import br.com.estudos.blogapi.model.dtos.output.FollowersOutputDTO;
import br.com.estudos.blogapi.model.dtos.output.FollowingOutputDTO;
import br.com.estudos.blogapi.model.entities.Follower;
import br.com.estudos.blogapi.model.entities.User;
import br.com.estudos.blogapi.repositories.FollowerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class FollowerService {

	private final FollowerRepository followerRepository;

	private final UserService userService;

	@Transactional
	public void create(String nickname, String logged) {

		if (isSameUser(nickname, logged)) {
			throw new NegocioException("Você não pode desseguir a você mesmo");
		}

		var userToFollow = findUserByNickname(nickname);

		var userLogged = findUserByNickname(logged);

		if (isFollow(userLogged, userToFollow)) {
			log.error("O " + userLogged.getNickname() + " já segue o " + userToFollow.getNickname());
			throw new NegocioException("Você já segue este usuário");
		}

		var follow = Follower.builder().id(null).follow(userLogged).followed(userToFollow).build();
		followerRepository.save(follow);

		log.info("Usuário seguido com sucesso");

	}

	@Transactional
	public void delete(String nickname, String logged) {

		if (isSameUser(nickname, logged)) {
			throw new NegocioException("Você não pode desseguir a você mesmo");
		}

		var userToUnfollow = findUserByNickname(nickname);

		var userLogged = findUserByNickname(logged);

		if (!isFollow(userLogged, userToUnfollow)) {
			log.error("O " + userLogged.getNickname() + " não segue o " + userToUnfollow.getNickname());
			throw new NegocioException("Você não segue este usuário para poder desseguir");
		}

		var unfollow = followerRepository.findByFollowAndFollowed(userLogged, userToUnfollow);

		followerRepository.deleteById(unfollow.getId());

		log.info("Usuário desseguido com sucesso");

	}

	public List<FollowersOutputDTO> findAllFollowers(String nickname) {

		var user = findUserByNickname(nickname);
		var followers = followerRepository.findAllByFollowed(user);
		return FollowersOutputMapper.INSTANCE.listaEntityToListaDTO(followers);

	}

	public List<FollowingOutputDTO> findAllFollowing(String nickname) {
		var user = findUserByNickname(nickname);
		var following = followerRepository.findAllByFollow(user);
		return FollowingOutputMapper.INSTANCE.listaEntityToListaDTO(following);
	}

	public Map<String, Integer> countFollower(String nickname) {
		var user = findUserByNickname(nickname);

		var following = followerRepository.countByFollow(user);
		var followers = followerRepository.countByFollowed(user);

		Map<String, Integer> countFollowers = new HashMap<>();

		countFollowers.put("following", following);
		countFollowers.put("followers", followers);
		return countFollowers;
	}

	private User findUserByNickname(String nickname) {
		return userService.findByNickname(nickname);
	}

	private boolean isFollow(User userLogged, User toFollow) {
		return followerRepository.existsByFollowAndFollowed(userLogged, toFollow);
	}

	private boolean isSameUser(String nickname, String logado) {

		if (nickname == logado) {
			return true;
		}

		return false;

	}

}
