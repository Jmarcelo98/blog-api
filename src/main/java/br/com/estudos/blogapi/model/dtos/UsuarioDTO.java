package br.com.estudos.blogapi.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	@Lob
	private byte[] foto;

	private String biografia;

	private LocalDate atualizadoEm;

	private String urlLinkedin;

	private String urlInstagram;

	private String urlWebSite;

}
