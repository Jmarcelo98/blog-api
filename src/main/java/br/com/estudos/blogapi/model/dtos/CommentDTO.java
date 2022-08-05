package br.com.estudos.blogapi.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDate createdAt;

	private String comment;
	
	private UserDTO user;

}
