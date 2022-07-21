package br.com.estudos.blogapi.configs.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

//@AllArgsConstructor
public class JWTValidateFilter extends BasicAuthenticationFilter {

	private final static String HEADER_ATRIBUTO = "Authorization";
	private final static String ATRIBUTO_PREFIXO = "Bearer ";

	public JWTValidateFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var atributo = request.getHeader(HEADER_ATRIBUTO);

		if (atributo == null) {
			chain.doFilter(request, response);
			return;
		}

		if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
			chain.doFilter(request, response);
			return;
		}

		var token = atributo.replace(ATRIBUTO_PREFIXO, "");

		var authenticationToken = getAuthenticationToken(token);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

		var usuario = JWT.require(Algorithm.HMAC512(JWTAuthenticateFilter.TOKEN_PASSWORD)).build().verify(token)
				.getSubject();

		if (usuario == null) {
			return null;
		}

		return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());

	}

}
