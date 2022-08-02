package br.com.estudos.blogapi.model.dtos.input;

import java.io.Serializable;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateInputDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String biography;

	private String urlLinkedin;

	private String urlInstagram;

	private String urlWebSite;

	@Lob
	private byte[] profilePicture;

}
