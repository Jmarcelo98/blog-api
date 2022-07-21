package br.com.estudos.blogapi.model.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationInputDTO {

	private String nickname;
	private String password;

}
