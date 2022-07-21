package br.com.estudos.blogapi.configs.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {

	public String getPrincipal() {

		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}

}
