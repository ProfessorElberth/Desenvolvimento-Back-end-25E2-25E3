package br.edu.infnet.pedidosapi.model.service;

import java.math.BigDecimal;

import br.edu.infnet.pedidosapi.interfaces.DescontoService;
import br.edu.infnet.pedidosapi.model.domain.ItemPedido;

public class StubDescontoServiceDez implements DescontoService {

    @Override
    public BigDecimal obterPercentualDesconto(ItemPedido itemPedido) {

        return new BigDecimal("0.10");
    }

}