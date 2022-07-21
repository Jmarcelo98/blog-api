package br.com.estudos.blogapi.configs.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.services.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetalheUsuarioImpl implements UserDetailsService {

	private final UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioService.buscarPorApelido(username);
		return new DetalheUsuario(usuario);
	}

}
