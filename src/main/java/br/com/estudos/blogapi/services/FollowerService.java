package br.com.estudos.blogapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.BusinessException;
import br.com.estudos.blogapi.mappers.output.FollowersOutputMapper;
import br.com.estudos.blogapi.mappers.output.FollowingOutputMapper;
import br.com.estudos.blogapi.mappers.output.UserOutputMapper;
import br.com.estudos.blogapi.model.dtos.output.UserOutputDTO;
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
	public void create(String nickname) {

		var userLogged = userService.getUserLogged();

		if (isSameUser(nickname, userLogged.getNickname())) {
			throw new BusinessException("Você não pode desseguir a você mesmo");
		}

		var userToFollow = findUserByNickname(nickname);

		if (isFollow(userLogged, userToFollow)) {
			log.error("O " + userLogged.getNickname() + " já segue o " + userToFollow.getNickname());
			throw new BusinessException("Você já segue este usuário");
		}

		var follow = Follower.builder().id(null).follow(userLogged).followed(userToFollow).createdAt(LocalDate.now())
				.build();

		followerRepository.save(follow);

		log.info("Usuário seguido com sucesso");

	}

	@Transactional
	public void delete(String nickname) {

		var userLogged = userService.getUserLogged();

		if (isSameUser(nickname, userLogged.getNickname())) {
			throw new BusinessException("Você não pode desseguir a você mesmo");
		}

		var userToUnfollow = findUserByNickname(nickname);

		if (!isFollow(userLogged, userToUnfollow)) {
			log.error("O " + userLogged.getNickname() + " não segue o " + userToUnfollow.getNickname());
			throw new BusinessException("Você não segue este usuário para poder desseguir");
		}

		var unfollow = followerRepository.findByFollowAndFollowed(userLogged, userToUnfollow);

		followerRepository.deleteById(unfollow.getId());

		log.info("Usuário desseguido com sucesso");

	}

	public List<UserOutputDTO> getMostFollowers() {

		var listMost = followerRepository.getMostFollowers();

		List<UserOutputDTO> listUserOutput = new ArrayList<>();

		for (int i = 0; i < listMost.size(); i++) {
			listUserOutput.add(getUserOutputDTOFromIdUser(listMost.get(i)));
		}

		return listUserOutput;

	}

	private UserOutputDTO getUserOutputDTOFromIdUser(Integer idUser) {
		var user = userService.findById(idUser);
		return UserOutputMapper.INSTANCE.entityToDTO(user);

	}

	public List<UserOutputDTO> findAllFollowers(String nickname) {

		var user = findUserByNickname(nickname);
		var followers = followerRepository.findAllByFollowedOrderByCreatedAtDesc(user);
		var listFollowers = FollowersOutputMapper.INSTANCE.listaEntityToListaDTO(followers);

		return listFollowers.stream()
				.map(obj -> new UserOutputDTO(obj.getFollow().getNickname(), obj.getFollow().getProfilePicture()))
				.collect(Collectors.toList());

	}

	public List<UserOutputDTO> findAllFollowing(String nickname) {

		var user = findUserByNickname(nickname);
		var following = followerRepository.findAllByFollowOrderByCreatedAtDesc(user);

		var listFollowing = FollowingOutputMapper.INSTANCE.listaEntityToListaDTO(following);

		return listFollowing.stream()
				.map(obj -> new UserOutputDTO(obj.getFollowed().getNickname(), obj.getFollowed().getProfilePicture()))
				.collect(Collectors.toList());
	}

	public Boolean isFollow(String nickname) {

		var userLogged = userService.getUserLogged();

		if (isSameUser(nickname, userLogged.getNickname())) {
			throw new BusinessException("Você não segue a você mesmo");
		}

		var userToFollow = findUserByNickname(nickname);

		return followerRepository.existsByFollowAndFollowed(userLogged, userToFollow);
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

		if (nickname.equals(logado)) {
			return true;
		}

		return false;

	}

}
