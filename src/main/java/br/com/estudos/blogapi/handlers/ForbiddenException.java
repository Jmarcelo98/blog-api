package br.com.estudos.blogapi.handlers;

public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String mensagem) {
		super(mensagem);
	}

}