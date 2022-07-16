package br.com.estudos.blogapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.estudos.blogapi.services.DBService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

	private final DBService dbService;

	@Bean
	public boolean instanciarBancoDeDados() {
		dbService.instanciarBandoDeDados();
		return true;
	}

}