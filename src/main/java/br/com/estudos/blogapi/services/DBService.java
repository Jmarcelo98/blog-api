package br.com.estudos.blogapi.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Category;
import br.com.estudos.blogapi.model.entities.Comment;
import br.com.estudos.blogapi.model.entities.Follower;
import br.com.estudos.blogapi.model.entities.Liked;
import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.User;
import br.com.estudos.blogapi.repositories.CategoryRepository;
import br.com.estudos.blogapi.repositories.CommentRepository;
import br.com.estudos.blogapi.repositories.FollowerRepository;
import br.com.estudos.blogapi.repositories.LikedRepository;
import br.com.estudos.blogapi.repositories.PostRepository;
import br.com.estudos.blogapi.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DBService {

	private final UserRepository userRepository;

	private final CategoryRepository categoryRepository;

	private final PostRepository postRepository;

	private final LikedRepository likedRepository;

	private final CommentRepository commentRepository;

	private final FollowerRepository followerRepository;

	public void instantiateDatabase() {

		LocalDate now = LocalDate.now();

		LocalDate dateCreatedJoao = LocalDate.of(2022, Month.JANUARY, 16);
		LocalDate dateCreatedSena = LocalDate.of(2022, Month.JANUARY, 16);

		LocalDate dateJaneiro = LocalDate.of(2022, Month.JANUARY, 16);
		LocalDate dateFevereiro = LocalDate.of(2021, Month.FEBRUARY, 16);
		LocalDate dateMarco = LocalDate.of(2021, Month.MARCH, 16);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		var user = User.builder().id(null).nickname("joao").name("João Marcelo").createdAt(dateCreatedJoao)
				.updatedAt(null).biography("Dev Full Stack Java").profilePicture(null).isPremium(false)
				.password(encoder.encode("123")).urlInstagram("https:www.instagram.com/jmarcelo098/")
				.urlLinkedin("https://www.linkedin.com/in/jmarcelo98/")
				.urlWebSite("https:jmarcelo98.github.io/portfolio/")
				.build();

		var user1 = User.builder().id(null).nickname("senavs").name("Matheus Sena").createdAt(dateCreatedSena)
				.updatedAt(null).biography("Engenheiro de Software").profilePicture(null).isPremium(false)
				.password(encoder.encode("123")).urlInstagram("https:www.instagram.com/senavs/")
				.urlLinkedin("www.linkedin.com/in/senavs/").urlWebSite("senavs.com").build();

		var user2 = User.builder().id(null).nickname("marcos").name("Marcos Giovanny").createdAt(now).updatedAt(null)
				.biography("Estagiário Dev").profilePicture(null).isPremium(false).password(encoder.encode("123"))
				.urlInstagram("https:www.instagram.com/marcosgiovanny/")
				.urlLinkedin("www.linkedin.com/in/marcos-giovanny/").urlWebSite(null).build();
		
		var user3 = User.builder().id(null).nickname("anonymousUser").name("Anonymus User").createdAt(now).updatedAt(null)
				.biography("Hacker").profilePicture(null).isPremium(false).password(encoder.encode("123"))
				.urlInstagram(null)
				.urlLinkedin(null).urlWebSite("https://en.wikipedia.org/wiki/Anonymous_(hacker_group)").build();

		userRepository.saveAll(Arrays.asList(user, user1, user2, user3));

		var category = Category.builder().id(null).description("Futebol").build();
		var category1 = Category.builder().id(null).description("Música").build();
		var category2 = Category.builder().id(null).description("eSports").build();
		var category3 = Category.builder().id(null).description("Tecnologia").build();

		categoryRepository.saveAll(Arrays.asList(category, category1, category2, category3));

		var post = Post.builder().id(null).createdAt(dateJaneiro).updatedAt(null).thumbnail(null)
				.description("O PODER DA GESTÃO NO FUTEBOL").category(category).title("SAF FUTEBOL").isPublished(true)
				.publishedAt(dateJaneiro).user(user)
				.content("É de conhecimento de todos, o grande roubo que aconteceu no "
						+ "Cruzeiro em 2019 pelos seus gestores! Após anos turbulentos e fracassados da equipe mineira, em dezembro de 2021,"
						+ " o clube mineiro por meio de votação do seus conselheiros, conseguiu alterar o estatuto do clube, permitindo a "
						+ "venda de 90% das ações da SAF, a qual foi adquirida por Ronaldo Fenômeno. Em 2022, a equipe celeste fez uma campanha boa na Copa do Brasil "
						+ "e até a data desta publicação, vem liderando a série B "
						+ "com 7 pontos de diferença para o Vasco da Gama. A torcida celeste finalmente está de bem com o time e sorridente!"
						+ "#ObrigadoRonaldo")
				.build();

		var post2 = Post.builder().id(null).createdAt(dateJaneiro).updatedAt(null).thumbnail(null)
				.description("A Imperial é isso tudo mesmo?").category(category2).title("IMPERIAL").isPublished(true)
				.publishedAt(dateFevereiro).user(user)
				.content(
						"A Imperial fez um Major incrivel, mas cai de produção, será que acabou a Imperial? Chegamos no momentos "
								+ "em que acontecerá trocas de bonecos?")
				.build();

		var post3 = Post.builder().id(null).createdAt(dateJaneiro).updatedAt(null).thumbnail(null)
				.description("A FURIA VAI GANHAR O MAJOR").category(category2).title("MAJOR RIO 2022")
				.isPublished(false).publishedAt(null).user(user2)
				.content("A Furia finalmente saiu do TOP 8 das competições para se sagrar CAMPEÃ MUNDIAL DE CSGOS")
				.build();

		postRepository.saveAll(Arrays.asList(post, post2, post3));

		var comment = Comment.builder().id(null).createdAt(dateFevereiro).post(post).user(user1)
				.comment("Muito bom o POST! A SAF é muito importante para o clubes brasileiros").build();

		var comment1 = Comment.builder().id(null).createdAt(dateMarco).post(post).user(user2).comment(
				"Creio que a SAF irá ajudar, mas precisamos lembrar que são empresários, vão pensar somente no bolso deles e não na torcida")
				.build();

		commentRepository.saveAll(Arrays.asList(comment, comment1));

		var liked = Liked.builder().id(null).createdAt(dateFevereiro).post(post).user(user1).build();
		var liked1 = Liked.builder().id(null).createdAt(dateMarco).post(post).user(user2).build();

		likedRepository.saveAll(Arrays.asList(liked, liked1));

		var follower = Follower.builder().id(null).follow(user).followed(user1).createdAt(dateCreatedSena).build();
		
		var follower1 = Follower.builder().id(null).follow(user1).followed(user).createdAt(dateJaneiro).build();
		
		var follower2 = Follower.builder().id(null).follow(user2).followed(user1).createdAt(dateCreatedSena).build();
		
		var follower3 = Follower.builder().id(null).follow(user2).followed(user).createdAt(now).build();

		followerRepository.saveAll(Arrays.asList(follower, follower1, follower2, follower3));

	}

}
