package br.com.estudos.blogapi.handlers;

public class RecursoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
