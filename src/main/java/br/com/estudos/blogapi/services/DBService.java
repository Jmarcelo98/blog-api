package br.com.estudos.blogapi.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.com.estudos.blogapi.model.entities.Categoria;
import br.com.estudos.blogapi.model.entities.Post;
import br.com.estudos.blogapi.model.entities.Usuario;
import br.com.estudos.blogapi.repositories.CategoriaRepository;
import br.com.estudos.blogapi.repositories.PostRepository;
import br.com.estudos.blogapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DBService {

	private final UsuarioRepository usuarioRepository;

	private final CategoriaRepository categoriaRepository;

	private final PostRepository postRepository;

	public void instanciarBandoDeDados() {

		LocalDate agora = LocalDate.now();

		LocalDate date1 = LocalDate.of(2021, Month.NOVEMBER, 16);

		var usuario = Usuario.builder().id(null).apelido("João Marcelo").criadoEm(agora).atualizadoEm(null)
				.biografia("Dev Full Stack Java").foto(null).isPremium(false).posts(null).senha("123")
				.redesSociais(null).redesSociais(null).build();

		usuarioRepository.saveAll(Arrays.asList(usuario));

		var categoria = Categoria.builder().id(null).descricao("Futebol").build();
		var categoria1 = Categoria.builder().id(null).descricao("Música").build();
		var categoria2 = Categoria.builder().id(null).descricao("eSports").build();
		var categoria3 = Categoria.builder().id(null).descricao("Tecnologia").build();

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria1, categoria2, categoria3));

		var post = Post.builder().id(null).criadoEm(date1).atualizadoEm(null).miniatura(null)
				.descricao("O PODER DA GESTÃO NO FUTEBOL").categoria(categoria).titulo("SAF FUTEBOL").isPublicado(true)
				.publicadoEm(date1)
				.conteudo("É de conhecimento de todos, o grande roubo que aconteceu no "
						+ "Cruzeiro em 2019 pelos seus gestores! Após anos turbulentos e fracassados da equipe mineira, em dezembro de 2021,"
						+ " o clube mineiro por meio de votação do seus conselheiros, conseguiu alterar o estatuto do clube, permitindo a "
						+ "venda de 90% das ações da SAF, a qual foi adquirida por Ronaldo Fenômeno. Em 2022, a equipe celeste fez uma campanha boa na Copa do Brasil "
						+ "e até a data desta publicação, vem liderando a série B "
						+ "com 7 pontos de diferença para o Vasco da Gama. A torcida celeste finalmente está de bem com o time e sorridente!"
						+ "#ObrigadoRonaldo")
				.build();

		postRepository.saveAll(Arrays.asList(post));

	}

}
