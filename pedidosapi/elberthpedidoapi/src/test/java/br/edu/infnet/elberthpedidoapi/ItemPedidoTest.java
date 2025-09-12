package br.edu.infnet.elberthpedidoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.elberthpedidoapi.model.domain.ItemPedido;
import br.edu.infnet.elberthpedidoapi.model.domain.Produto;
import br.edu.infnet.elberthpedidoapi.model.domain.TipoProduto;

public class ItemPedidoTest {

	private Produto produto;

	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	@DisplayName("Deve realizar o cálculo do subtotal para um item válido.")
	void deveCalcularSubTotal_quandoItemPedidoValido() {
		// Dado: um item de pedido com produto e quantidade válidos
		String codigo = "1";
		String nome = "Um livro";
		TipoProduto tipo = TipoProduto.LIVRO;
		BigDecimal valor = new BigDecimal("2.50");
		int quantidade = 4;
		
		produto = new Produto(codigo, nome, tipo, valor);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(quantidade);
		
		BigDecimal subTotalEsperado = new BigDecimal("10.00");
		
		// Quando: chamar o método calcularSubTotal
		BigDecimal subTotalCalculado = itemPedido.calcularSubTotal();
		
		// Então: o resultado do subTotal será o valor esperado
		assertEquals(subTotalEsperado, subTotalCalculado, "O subTotal deve ser 10.00 para esse item");

		assertEquals(codigo, produto.getCodigo(), "O código utilizado na criação do produto deve ser '1'");
		assertEquals(nome, produto.getNome(), "O nome utilizado na criação do produto deve ser 'Um livro'");
		assertEquals(tipo, produto.getTipo(), "O tipo utilizado na criação do produto deve ser 'LIVRO'");
		assertEquals(valor, produto.getValor(), "O valor utilizado na criação do produto deve ser 2.50");
		assertEquals(quantidade, itemPedido.getQuantidade(), "A quantidade do item utilizado na criação deve ser 4");
		assertNotNull(itemPedido.getProduto());
	}

	@Test
	@DisplayName("Deve retornar zero quando a quantidade for zero.")
	void deveRetornarZero_quandoQuantidadeForZero() {
		// Dado: um item de pedido com produto e quantidade válidos
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setQuantidade(0);
		
		BigDecimal subTotalEsperado = BigDecimal.ZERO;
		
		// Quando: chamar o método calcularSubTotal
		BigDecimal subTotalCalculado = itemPedido.calcularSubTotal();
		
		// Então: o resultado do subTotal será o valor esperado
		assertEquals(subTotalEsperado, subTotalCalculado, "O subTotal deve ser zero quando a quantidade estiver zerada.");	
	}

	@Test
	@DisplayName("Deve retornar zero quando a quantidade for negativa.")
	void deveRetornarZero_quandoQuantidadeForNegativa() {
		// Dado: um item de pedido com produto e quantidade válidos
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setQuantidade(-1);
		
		BigDecimal subTotalEsperado = BigDecimal.ZERO;
		
		// Quando: chamar o método calcularSubTotal
		BigDecimal subTotalCalculado = itemPedido.calcularSubTotal();
		
		// Então: o resultado do subTotal será o valor esperado
		assertEquals(subTotalEsperado, subTotalCalculado, "O subTotal deve ser zero quando a quantidade estiver negativa.");	
	}

	@Test
	@DisplayName("Deve retornar zero quando o produto estiver nulo.")
	void deveRetornarZero_quandoProdutoNulo() {
		// Dado: um item de pedido com produto e quantidade válidos
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(null);
		itemPedido.setQuantidade(4);
		
		BigDecimal subTotalEsperado = BigDecimal.ZERO;
		
		// Quando: chamar o método calcularSubTotal
		BigDecimal subTotalCalculado = itemPedido.calcularSubTotal();
		
		// Então: o resultado do subTotal será o valor esperado
		assertEquals(subTotalEsperado, subTotalCalculado, "O subTotal deve ser zero quando a o produto estiver nulo.");	
	}

	@Test
	@DisplayName("Deve retornar zero quando o valor do produto estiver nulo.")
	void deveRetornarZero_quandoValorProdutoNulo() {
		// Dado: um item de pedido com produto e quantidade válidos
		produto = new Produto("1", "Um livro", TipoProduto.LIVRO, null);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(4);
		
		BigDecimal subTotalEsperado = BigDecimal.ZERO;
		
		// Quando: chamar o método calcularSubTotal
		BigDecimal subTotalCalculado = itemPedido.calcularSubTotal();
		
		// Então: o resultado do subTotal será o valor esperado
		assertEquals(subTotalEsperado, subTotalCalculado, "O subTotal deve ser zero quando o valor do produto estiver nulo.");	
	}
}