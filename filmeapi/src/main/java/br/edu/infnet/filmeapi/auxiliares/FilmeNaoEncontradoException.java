package br.edu.infnet.filmeapi.auxiliares;

public class FilmeNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilmeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}	
}