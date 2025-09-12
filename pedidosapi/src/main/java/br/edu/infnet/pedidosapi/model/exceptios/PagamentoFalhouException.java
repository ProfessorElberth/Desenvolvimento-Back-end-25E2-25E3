package br.edu.infnet.pedidosapi.model.exceptios;

public class PagamentoFalhouException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public PagamentoFalhouException(String message) {
        super(message);
    }

}