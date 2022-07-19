package br.com.estudos.blogapi.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	private byte[] miniatura;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String titulo;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String conteudo;

	private Boolean isPublicado;

	private LocalDate publicadoEm;

	@Column(nullable = false, updatable = false)
	private LocalDate criadoEm;

	private LocalDate atualizadoEm;

	@OneToMany(mappedBy = "post", orphanRemoval = true)
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "post", orphanRemoval = true)
	private List<Curtida> curtidas;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false, updatable = false)
	private Categoria categoria;

}
