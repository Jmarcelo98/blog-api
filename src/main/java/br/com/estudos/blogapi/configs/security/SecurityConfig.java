package br.com.estudos.blogapi.configs.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

	private final String[] ACESSO_PUBLIC_CONTROLLERS = { "/categories" };

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailImpl).passwordEncoder(passwordEncoder);
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().disable();
//		http.cors().configurationSource(RequestBody -> new CorsConfiguration().applyPermitDefaultValues());
//		http.csrf().disable().authorizeHttpRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
//				.antMatchers(ACESSO_PUBLIC_CONTROLLERS).permitAll().antMatchers(ACESSO).permitAll().anyRequest()
//				.authenticated().and().addFilter(new JWTAuthenticateFilter(authenticationManager()))
//				.addFilter(new JWTValidateFilter(authenticationManager())).sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(ACESSO_PUBLIC_CONTROLLERS).permitAll().antMatchers(ACESSO).permitAll().anyRequest()
				.authenticated().and().addFilter(new JWTAuthenticateFilter(authenticationManager()))
				.addFilter(new JWTValidateFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf().disable();
	}

	@Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader("*");
        config.addAllowedOriginPattern("*");
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.addExposedHeader("Authorization");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
	
//
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		var source = new UrlBasedCorsConfigurationSource();
//		var corConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		source.registerCorsConfiguration("/**", corConfiguration);
//		return source;
//	}

}
