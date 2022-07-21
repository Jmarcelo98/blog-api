package br.com.estudos.blogapi.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Categoria;
import br.com.estudos.blogapi.model.entities.Comentario;
import br.com.estudos.blogapi.model.entities.Curtida;
import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.Seguidor;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.CategoriaRepository;
import br.com.estudos.blogapi.repositories.ComentarioRepository;
import br.com.estudos.blogapi.repositories.CurtidaRepository;
import br.com.estudos.blogapi.repositories.PostRepository;
import br.com.estudos.blogapi.repositories.SeguidorRepository;
import br.com.estudos.blogapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DBService {

	private final UsuarioRepository usuarioRepository;

	private final CategoriaRepository categoriaRepository;

	private final PostRepository postRepository;

	private final CurtidaRepository curtidaRepository;

	private final ComentarioRepository comentarioRepository;

	private final SeguidorRepository seguidorRepository;

	public void instanciarBandoDeDados() {

		LocalDate agora = LocalDate.now();

		LocalDate dateMarco2021 = LocalDate.of(2022, Month.JANUARY, 16);
		LocalDate dateMaio2021 = LocalDate.of(2022, Month.JANUARY, 16);

		LocalDate dateJaneiro = LocalDate.of(2022, Month.JANUARY, 16);
		LocalDate dateFevereiro = LocalDate.of(2021, Month.FEBRUARY, 16);
		LocalDate dateMarco = LocalDate.of(2021, Month.MARCH, 16);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		var usuario = Usuario.builder().id(null).apelido("joao").nome("João Marcelo").criadoEm(dateMarco2021)
				.atualizadoEm(null).biografia("Dev Full Stack Java").foto(null).isPremium(false).posts(null)
				.senha(encoder.encode("123")).urlInstagram("https://www.instagram.com/jmarcelo098/")
				.urlLinkedin("www.linkedin.com/in/jmarcelo98/").urlWebSite("https://jmarcelo98.github.io/portfolio/")
				.build();

		var usuario1 = Usuario.builder().id(null).apelido("senavs").nome("Matheus Sena").criadoEm(dateMaio2021)
				.atualizadoEm(null).biografia("Engenheiro de Software").foto(null).isPremium(false).posts(null)
				.senha(encoder.encode("123")).urlInstagram("https://www.instagram.com/senavs/")
				.urlLinkedin("www.linkedin.com/in/senavs/").urlWebSite("senavs.com").build();

		var usuario2 = Usuario.builder().id(null).apelido(".Marcos").nome("Marcos Giovanny").criadoEm(agora)
				.atualizadoEm(null).biografia("Estagiário Dev").foto(null).isPremium(false).posts(null).senha(encoder.encode("123"))
				.urlInstagram("https://www.instagram.com/marcosgiovanny/")
				.urlLinkedin("www.linkedin.com/in/marcos-giovanny/").urlWebSite(null).build();

		usuarioRepository.saveAll(Arrays.asList(usuario, usuario1, usuario2));

		var categoria = Categoria.builder().id(null).descricao("Futebol").build();
		var categoria1 = Categoria.builder().id(null).descricao("Música").build();
		var categoria2 = Categoria.builder().id(null).descricao("eSports").build();
		var categoria3 = Categoria.builder().id(null).descricao("Tecnologia").build();

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria1, categoria2, categoria3));

		var post = Post.builder().id(null).criadoEm(dateJaneiro).atualizadoEm(null).miniatura(null)
				.descricao("O PODER DA GESTÃO NO FUTEBOL").categoria(categoria).titulo("SAF FUTEBOL").isPublicado(true)
				.publicadoEm(dateJaneiro).usuario(usuario)
				.conteudo("É de conhecimento de todos, o grande roubo que aconteceu no "
						+ "Cruzeiro em 2019 pelos seus gestores! Após anos turbulentos e fracassados da equipe mineira, em dezembro de 2021,"
						+ " o clube mineiro por meio de votação do seus conselheiros, conseguiu alterar o estatuto do clube, permitindo a "
						+ "venda de 90% das ações da SAF, a qual foi adquirida por Ronaldo Fenômeno. Em 2022, a equipe celeste fez uma campanha boa na Copa do Brasil "
						+ "e até a data desta publicação, vem liderando a série B "
						+ "com 7 pontos de diferença para o Vasco da Gama. A torcida celeste finalmente está de bem com o time e sorridente!"
						+ "#ObrigadoRonaldo")
				.build();

		var post2 = Post.builder().id(null).criadoEm(dateJaneiro).atualizadoEm(null).miniatura(null)
				.descricao("A Imperial é isso tudo mesmo?").categoria(categoria2).titulo("IMPERIAL").isPublicado(true)
				.publicadoEm(dateFevereiro).usuario(usuario)
				.conteudo(
						"A Imperial fez um Major incrivel, mas cai de produção, será que acabou a Imperial? Chegamos no momentos "
								+ "em que acontecerá trocas de bonecos?")
				.build();

		var post3 = Post.builder().id(null).criadoEm(dateJaneiro).atualizadoEm(null).miniatura(null)
				.descricao("A FURIA VAI GANHAR O MAJOR").categoria(categoria2).titulo("MAJOR RIO 2022")
				.isPublicado(false).publicadoEm(null).usuario(usuario)
				.conteudo("A Furia finalmente saiu do TOP 8 das competições para se sagrar CAMPEÃ MUNDIAL DE CSGOS")
				.build();

		postRepository.saveAll(Arrays.asList(post, post2, post3));

		var comentario = Comentario.builder().id(null).criadoEm(dateFevereiro).post(post).usuario(usuario1)
				.comentario("Muito bom o POST! A SAF é muito importante para o clubes brasileiros").build();

		var comentario1 = Comentario.builder().id(null).criadoEm(dateMarco).post(post).usuario(usuario2).comentario(
				"Creio que a SAF irá ajudar, mas precisamos lembrar que são empresários, vão pensar somente no bolso deles e não na torcida")
				.build();

		comentarioRepository.saveAll(Arrays.asList(comentario, comentario1));

		var curtida = Curtida.builder().id(null).criadoEm(dateFevereiro).post(post).usuario(usuario1).build();
		var curtida1 = Curtida.builder().id(null).criadoEm(dateMarco).post(post).usuario(usuario2).build();

		curtidaRepository.saveAll(Arrays.asList(curtida, curtida1));

		var seguidor = Seguidor.builder().id(null).segue(usuario).seguido(usuario1).build();
		var seguidor1 = Seguidor.builder().id(null).segue(usuario1).seguido(usuario).build();
		var seguidor2 = Seguidor.builder().id(null).segue(usuario2).seguido(usuario1).build();
		var seguidor3 = Seguidor.builder().id(null).segue(usuario2).seguido(usuario).build();

		seguidorRepository.saveAll(Arrays.asList(seguidor, seguidor1, seguidor2, seguidor3));

	}

}
