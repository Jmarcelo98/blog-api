package br.com.estudos.blogapi.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Column(nullable = false, unique = true)
	private String apelido;

	@Column(nullable = false)
	private String senha;

	private Boolean isPremium;

	@Lob
	private byte[] foto;

	private String biografia;

	private String urlLinkedin;

	private String urlInstagram;

	private String urlWebSite;

	@Column(nullable = false, updatable = false)
	private LocalDate criadoEm;

	private LocalDate atualizadoEm;

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(mappedBy = "segue", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seguidor> seguidores;

	@OneToMany(mappedBy = "seguido", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seguidor> seguindo;

}
