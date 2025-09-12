package br.edu.infnet.pedidosapi.auxiliar;

import br.edu.infnet.pedidosapi.model.domain.ItemPedido;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.domain.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PedidoHelper {

    public static Pedido newPedido(Long id, ItemPedido... itens) {
        List<ItemPedido> itensList = (itens != null) ? Arrays.asList(itens) : new ArrayList<>();
        Pedido pedido = new Pedido(id, itensList);
        return pedido;
    }

    public static ItemPedido newItemPedido(Produto produto, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade do item pedido não pode ser negativa.");
        }
        return new ItemPedido(produto, quantidade);
    }

    public static Produto newProduto(Long id, String nome, double preco) {
        return new Produto(id, nome, BigDecimal.valueOf(preco));
    }
}