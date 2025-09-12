package br.edu.infnet.pedidosapi.model.service;

import br.edu.infnet.pedidosapi.interfaces.AutorizadorPedidoService;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.exceptios.PedidoNaoAutorizadoException;

public class GestaoPedidoService {

    private final AutorizadorPedidoService autorizadorPedidoService;

    public GestaoPedidoService(AutorizadorPedidoService autorizadorPedidoService) {
        this.autorizadorPedidoService = autorizadorPedidoService;
    }

    public Pedido incluir(Pedido pedido) {
        if(!autorizadorPedidoService.autorizar(pedido)) {
            throw new PedidoNaoAutorizadoException("O pedido não foi autorizado!");
        }

        if (pedido.getId() == null) {
            pedido.setId(1L);
        }

        return pedido;
    }
}