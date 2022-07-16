package br.com.estudos.blogapi.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String apelido;

	private String senha;

	private Boolean isPremium;

	private byte[] foto;

	private String biografia;

	private LocalDate criadoEm;

	private LocalDate atualizadoEm;

	@JoinColumn(name = "id_redes_sociais")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private RedesSociais redesSociais;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;

}
