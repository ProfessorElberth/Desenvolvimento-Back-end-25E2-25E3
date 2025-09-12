package br.edu.infnet.pedidosapi.interfaces;

import java.math.BigDecimal;

import br.edu.infnet.pedidosapi.model.domain.ItemPedido;

public interface DescontoService {

	BigDecimal obterPercentualDesconto(ItemPedido itemPedido);
}