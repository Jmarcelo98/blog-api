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
	private byte[] miniatura;

	private String descricao;

	private String titulo;

	private String conteudo;

	private Boolean isPublicado;

	private LocalDate publicadoEm;
	
	private LocalDate criadoEm;

	private LocalDate atualizadoEm;

	private CategoriaDTO categoria;

}
