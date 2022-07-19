package br.com.estudos.blogapi.model.dtos.output;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguindoOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioOutputDTO seguido;

}
