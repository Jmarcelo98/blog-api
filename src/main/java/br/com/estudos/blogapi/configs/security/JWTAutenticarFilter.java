package br.com.estudos.blogapi.configs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.estudos.blogapi.configs.security.service.DetalheUsuario;
import br.com.estudos.blogapi.model.entities.Usuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

	private static final int TOKEN_EXPIRACAO = 1800_000;

	public static final String TOKEN_SENHA = "261fcbc0-a023-46c8-a783-57d155d6363b";

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			var usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getApelido(),
					usuario.getSenha(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		var detalheUsuario = (DetalheUsuario) authResult.getPrincipal();

		var token = JWT.create().withSubject(detalheUsuario.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));

		response.getWriter().write(token);
		response.getWriter().flush();

	}

}
