package br.edu.infnet.pedidosapi.interfaces;

import br.edu.infnet.pedidosapi.model.domain.Pedido;

public interface AutorizadorPedidoService {

	boolean autorizar(Pedido pedido);
}