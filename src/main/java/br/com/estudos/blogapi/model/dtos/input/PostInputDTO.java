package br.com.estudos.blogapi.model.dtos.input;

import br.com.estudos.blogapi.model.dtos.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInputDTO {

	private String title;
	private String description;
	private String content;
	private Boolean isPublished;
	private byte[] thumbnail;
	private CategoryDTO category;
}