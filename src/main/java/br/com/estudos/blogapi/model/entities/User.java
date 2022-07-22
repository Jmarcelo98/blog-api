package br.com.estudos.blogapi.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	private String password;

	private Boolean isPremium;

	@Lob
	private byte[] profilePicture;

	private String biography;

	private String urlLinkedin;

	private String urlInstagram;

	private String urlWebSite;

	@Column(nullable = false, updatable = false)
	private LocalDate createdAt;

	private LocalDate updatedAt;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(mappedBy = "follow", orphanRemoval = true)
	@JsonIgnore
	private List<Follower> followers;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonIgnore
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@JsonIgnore
	private List<Liked> likeds;

	@OneToMany(mappedBy = "followed", orphanRemoval = true)
	@JsonIgnore
	private List<Follower> following;

}
