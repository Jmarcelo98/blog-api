package br.com.estudos.blogapi.configs.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.services.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userService.findByNickname(username);
		return new UserDetail(user);
	}

}
