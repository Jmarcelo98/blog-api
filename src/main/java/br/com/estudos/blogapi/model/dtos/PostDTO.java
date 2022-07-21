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
public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Lob
	private byte[] thumbnail;

	private String description;

	private String title;

	private String content;

	private Boolean isPublished;

	private LocalDate publishedAt;
	
	private LocalDate createdAt;

	private LocalDate updatedAt;

	private CategoryDTO category;

}
