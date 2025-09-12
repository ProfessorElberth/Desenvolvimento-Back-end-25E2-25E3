package br.edu.infnet.pedidosapi.interfaces;

import java.math.BigDecimal;

import br.edu.infnet.pedidosapi.model.domain.Cliente;
import br.edu.infnet.pedidosapi.model.exceptios.PagamentoFalhouException;

public interface GatewayPagamento {
	boolean processarPagamento(BigDecimal valorTotal, Cliente cliente) throws PagamentoFalhouException;
}