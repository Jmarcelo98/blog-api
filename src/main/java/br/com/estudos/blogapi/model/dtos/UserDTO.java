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
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nickname;
	
	private String name;

	@Lob
	private byte[] profilePicture;

	private Boolean isPremium;

	private String biography;

	private LocalDate updatedAt;

	private String urlLinkedin;

	private String urlInstagram;

	private String urlWebSite;

}
