package br.com.estudos.blogapi.configs.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.estudos.blogapi.handlers.NegocioException;
import br.com.estudos.blogapi.handlers.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex,
			WebRequest request) {

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		var status = HttpStatus.NOT_FOUND;
		var body = new ResponseError();

		body.setCode(status.value());
		body.setDescricao(ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioExceptionException(NegocioException ex, WebRequest request) {

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		var status = HttpStatus.BAD_REQUEST;

		var body = new ResponseError();
		body.setCode(status.value());
		body.setDescricao(ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

}
