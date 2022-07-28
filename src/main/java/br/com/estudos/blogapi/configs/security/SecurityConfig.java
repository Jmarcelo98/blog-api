package br.com.estudos.blogapi.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import br.com.estudos.blogapi.configs.security.service.UserDetailImpl;
import lombok.AllArgsConstructor;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailImpl userDetailImpl;
	private final PasswordEncoder passwordEncoder;

	private final String[] ACESSO = { "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
			"/configuration/security", "/swagger-ui.html", "/webjars/**", "/h2/**" };

	private final String[] ACESSO_CONTROLLERS = { "/users/**" };
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailImpl).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.cors().configurationSource(RequestBody -> new CorsConfiguration().applyPermitDefaultValues());
		http.csrf().disable().authorizeHttpRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(ACESSO_CONTROLLERS).permitAll().antMatchers(HttpMethod.GET, "/categories")
				.permitAll().antMatchers(ACESSO).permitAll().anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticateFilter(authenticationManager()))
				.addFilter(new JWTValidateFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		var source = new UrlBasedCorsConfigurationSource();
		var corConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corConfiguration);
		return source;
	}

}
