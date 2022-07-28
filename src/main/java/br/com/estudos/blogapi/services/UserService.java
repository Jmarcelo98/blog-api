package br.com.estudos.blogapi.services;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.handlers.BusinessException;
import br.com.estudos.blogapi.handlers.ResourceNotFoundException;
import br.com.estudos.blogapi.mappers.UserMapper;
import br.com.estudos.blogapi.model.dtos.UserDTO;
import br.com.estudos.blogapi.model.dtos.input.UserRegistrationInputDTO;
import br.com.estudos.blogapi.model.entities.User;
import br.com.estudos.blogapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public void create(UserRegistrationInputDTO userRegistrationInputDTO) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (existsByNickname(userRegistrationInputDTO.getNickname())) {
			log.error("Apelido: " + userRegistrationInputDTO.getNickname() + " já existe");
			throw new BusinessException("Apelido já existe");
		}

		var newUser = User.builder().nickname(userRegistrationInputDTO.getNickname()).isPremium(false)
				.password(encoder.encode(userRegistrationInputDTO.getPassword())).createdAt(LocalDate.now()).build();

		userRepository.save(newUser);

		log.info("Usuário cadastrado com sucesso");

	}

	@Transactional
	public void update(UserDTO userDTO, String nickname) {

		var userLogged = findByNickname(nickname);

		BeanUtils.copyProperties(userDTO, userLogged);

		userRepository.save(userLogged);

		log.info("Usuário atualizado com sucesso");

	}

	@Transactional
	public void delete(String nickname) {
		userRepository.deleteByNickname(nickname);
		log.info("Usuário deletado com sucesso");
	}

	public UserDTO findByNicknameAndConvertDTO(String nickname) {
		var user = userRepository.findByNicknameIgnoreCase(nickname)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado através do apelido"));

		return UserMapper.INSTANCE.entityToDTO(user);

	}

	public User findByNickname(String nickname) {
		return userRepository.findByNicknameIgnoreCase(nickname)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado através do apelido"));
	}

	private Boolean existsByNickname(String nickname) {
		return userRepository.existsByNicknameIgnoreCase(nickname);
	}

}
