package br.com.estudos.blogapi.model.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInputDTO {

	private String comment;
	private Integer idPost;

}
