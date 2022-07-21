package br.com.estudos.blogapi.configs.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.estudos.blogapi.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DetalheUsuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Usuario usuario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return usuario.getId();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getApelido();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
