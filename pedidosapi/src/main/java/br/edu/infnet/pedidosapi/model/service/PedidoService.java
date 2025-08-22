package br.edu.infnet.pedidosapi.model.service;

import java.math.BigDecimal;

import br.edu.infnet.pedidosapi.model.domain.ItemPedido;
import br.edu.infnet.pedidosapi.model.domain.Pedido;

public class PedidoService {

	public BigDecimal calcularSubTotal(Pedido pedido) {
		
		// validação: pedido null, itens do pedido = null
		
		// obter o pedido, percorrer os itens do pedido, pegar o produto e a quantidade, pegar o valor do produto, multiplicar o valor pela quantidade
		
		BigDecimal subTotal = BigDecimal.ZERO; 
		
		for(ItemPedido item : pedido.getItens()) {
			// validação: item null, produto do item null, quantidade maior que zero, preço null
			
			BigDecimal preco = item.getProduto().getPreco();
			BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());
			
			BigDecimal valorItem = preco.multiply(quantidade);

			subTotal = subTotal.add(valorItem);
		}
				
		return subTotal;
	}
}