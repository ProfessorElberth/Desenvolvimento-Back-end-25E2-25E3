package br.edu.infnet.filmeapi.auxiliares;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FilmeNaoEncontradoException.class)
	public ResponseEntity<Object> handlerFilmeNaoEncontradoException(FilmeNaoEncontradoException e){
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("datahora", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DadosInvalidosException.class)
	public ResponseEntity<Object> handlerDadosInvalidosException(DadosInvalidosException e){
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("datahora", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}