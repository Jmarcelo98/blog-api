package br.com.estudos.blogapi.model.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String description;

}
