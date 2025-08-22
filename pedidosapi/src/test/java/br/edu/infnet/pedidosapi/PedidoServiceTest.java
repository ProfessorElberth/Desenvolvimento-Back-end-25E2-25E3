package br.edu.infnet.pedidosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.pedidosapi.model.domain.ItemPedido;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.domain.Produto;
import br.edu.infnet.pedidosapi.model.service.PedidoService;

public class PedidoServiceTest {

	private PedidoService pedidoService;
	
	@BeforeEach
	void setUp() {
		pedidoService = new PedidoService();
	}
	
	@Test
	@DisplayName("Deve calcular o subtotal para itens de pedido validos.")
	void deveCalcularSubTotal_quantoItensValidos_entaoValorCorreto() {
		//Dado
		Produto suco = new Produto(1L, "Suco", new BigDecimal(9.50));
		Produto pao = new Produto(2L, "Pão", new BigDecimal(2.50));
		
		ItemPedido iSuco = new ItemPedido(suco, 4); //38
		ItemPedido iPao = new ItemPedido(pao, 10); //25
		
		Pedido pedido = new Pedido(1L, Arrays.asList(iSuco, iPao));
		
		BigDecimal subTotal = new BigDecimal("63.0");
		
		// Quando
		BigDecimal subTotalCalculado = pedidoService.calcularSubTotal(pedido);
		
		// Então
		assertEquals(subTotal, subTotalCalculado, "O subtotal deveria ser R$"+subTotal);
	}

	
	@Test
	@DisplayName("Deve retornar zero para um pedido sem itens.")
	void deveCalcularSubTotal_quantoPedidoSemItens_entaoRetornarZero() {
		//Dado				
		Pedido pedidoVazio = new Pedido(1L, Collections.emptyList());
		BigDecimal subTotal = BigDecimal.ZERO;
		
		// Quando
		BigDecimal subTotalCalculado = pedidoService.calcularSubTotal(pedidoVazio);
		
		// Então
		assertEquals(subTotal, subTotalCalculado, "O subtotal em pedidos vazios deveria ser zerado");
		
	}
}
