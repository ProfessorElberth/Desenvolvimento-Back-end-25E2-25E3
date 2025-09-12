package br.edu.infnet.elberthpedidoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.elberthpedidoapi.model.domain.ItemPedido;
import br.edu.infnet.elberthpedidoapi.model.domain.Pedido;
import br.edu.infnet.elberthpedidoapi.model.domain.Produto;
import br.edu.infnet.elberthpedidoapi.model.domain.TipoProduto;
import br.edu.infnet.elberthpedidoapi.model.service.PedidoService;

class PedidoServiceTest {

    private PedidoService pedidoService;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService();
        // Produtos de exemplo para reutilização nos testes
        produto1 = new Produto("PROD001", "Livro TDD", TipoProduto.LIVRO, new BigDecimal("50.00"));
        produto2 = new Produto("PROD002", "Camiseta Java", TipoProduto.ROUPA, new BigDecimal("75.00"));
    }

    // --- RF003.01: Cálculo do Valor Total do Pedido ---

    @Test
    @DisplayName("RF003.01 - Deve calcular o valor total para um pedido com um único item.")
    void deveCalcularValorTotal_quandoPedidoPossuirUmItem() {
        // Dado: um pedido com um ItemPedido
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto1);
        itemPedido.setQuantidade(2); // 2 * 50.00 = 100.00

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(itemPedido);

        Pedido pedido = new Pedido();
        pedido.setItens(listaItens);

        BigDecimal valorTotalEsperado = new BigDecimal("100.00");

        // Quando: calcularValorTotal é chamado
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedido);

        // Então: o valor total deve ser o esperado
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total do pedido com um item deve ser 100.00");
    }

    @Test
    @DisplayName("RF003.01 - Deve calcular o valor total para um pedido com vários itens.")
    void deveCalcularValorTotal_quandoPedidoPossuirVariosItens() {
        // Dado: um pedido com vários ItemPedidos
        ItemPedido item1 = new ItemPedido();
        item1.setProduto(produto1);
        item1.setQuantidade(2); // 2 * 50.00 = 100.00

        ItemPedido item2 = new ItemPedido();
        item2.setProduto(produto2);
        item2.setQuantidade(3); // 3 * 75.00 = 225.00

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(item1);
        listaItens.add(item2);

        Pedido pedido = new Pedido();
        pedido.setItens(listaItens);

        BigDecimal valorTotalEsperado = new BigDecimal("325.00"); // 100.00 + 225.00

        // Quando: calcularValorTotal é chamado
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedido);

        // Então: o valor total deve ser a soma dos subtotais
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total do pedido com vários itens deve ser 325.00");
    }

    @Test
    @DisplayName("RF003.01 - Deve retornar zero quando o pedido não tiver itens.")
    void deveRetornarZero_quandoPedidoNaoTemItens() {
        // Dado: um pedido com a lista de itens vazia
        Pedido pedido = new Pedido();
        pedido.setItens(new ArrayList<>()); // Lista vazia

        BigDecimal valorTotalEsperado = BigDecimal.ZERO;

        // Quando: calcularValorTotal é chamado
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedido);

        // Então: o valor total deve ser zero
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total do pedido sem itens deve ser zero.");
    }

    @Test
    @DisplayName("RF003.01 - Deve retornar zero quando a lista de itens do pedido for nula.")
    void deveRetornarZero_quandoListaDeItensNula() {
        // Dado: um pedido com a lista de itens nula
        Pedido pedido = new Pedido();
        pedido.setItens(null); // Lista nula

        BigDecimal valorTotalEsperado = BigDecimal.ZERO;

        // Quando: calcularValorTotal é chamado
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedido);

        // Então: o valor total deve ser zero
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total do pedido com lista de itens nula deve ser zero.");
    }

    @Test
    @DisplayName("RF003.01 - Deve retornar zero quando o pedido for nulo.")
    void deveRetornarZero_quandoPedidoForNulo() {
        // Dado: um pedido nulo
        Pedido pedidoNulo = null;

        BigDecimal valorTotalEsperado = BigDecimal.ZERO;

        // Quando: calcularValorTotal é chamado com pedido nulo
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedidoNulo);

        // Então: o valor total deve ser zero
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total do pedido nulo deve ser zero.");
    }

    @Test
    @DisplayName("RF003.01 - Deve somar corretamente itens com quantidades diferentes.")
    void deveSomarCorretamenteItensComQuantidadesDiferentes() {
        // Dado: pedido com itens de quantidades variadas
        ItemPedido item1 = new ItemPedido();
        item1.setProduto(produto1); // 50.00
        item1.setQuantidade(1); // 1 * 50.00 = 50.00

        ItemPedido item2 = new ItemPedido();
        item2.setProduto(produto2); // 75.00
        item2.setQuantidade(5); // 5 * 75.00 = 375.00

        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(item1);
        listaItens.add(item2);

        Pedido pedido = new Pedido();
        pedido.setItens(listaItens);

        BigDecimal valorTotalEsperado = new BigDecimal("425.00"); // 50.00 + 375.00

        // Quando: calcularValorTotal é chamado
        BigDecimal valorTotalCalculado = pedidoService.calcularValorTotal(pedido);

        // Então: o valor total deve ser a soma correta
        assertEquals(valorTotalEsperado.compareTo(valorTotalCalculado), 0, "O total deve somar corretamente itens com quantidades diferentes.");
    }

    // --- RF003.02: Aplicação de Desconto Percentual ---

    @Test
    @DisplayName("RF003.02 - Deve aplicar o desconto corretamente quando o percentual é válido.")
    void deveAplicarDescontoCorretamente_quandoPercentualValido() {
        // Dado: um pedido com valor total de 100.00 e um desconto de 10%
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(2); // Valor total base: 100.00

        Pedido pedido = new Pedido();
        pedido.setItens(List.of(item));

        BigDecimal percentualDesconto = new BigDecimal("0.10"); // 10%
        BigDecimal valorFinalEsperado = new BigDecimal("90.00"); // 100.00 - (100.00 * 0.10) = 90.00

        // Quando: aplicarDescontoPercentual é chamado
        BigDecimal valorFinalCalculado = pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto);

        // Então: o valor final do pedido deve ser o esperado
        assertEquals(valorFinalEsperado.compareTo(valorFinalCalculado), 0, "O desconto de 10% deve ser aplicado corretamente.");
    }

    @Test
    @DisplayName("RF003.02 - Se o desconto for zero, o valor final do pedido não deve mudar.")
    void deveManterValor_quandoDescontoForZero() {
        // Dado: um pedido com valor total de 100.00 e um desconto de 0%
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(2);

        Pedido pedido = new Pedido();
        pedido.setItens(List.of(item));

        BigDecimal percentualDesconto = BigDecimal.ZERO; // 0%
        BigDecimal valorFinalEsperado = new BigDecimal("100.00"); // 100.00 - (100.00 * 0) = 100.00

        // Quando: aplicarDescontoPercentual é chamado
        BigDecimal valorFinalCalculado = pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto);

        // Então: o valor final do pedido deve ser igual ao valor total original
        assertEquals(valorFinalEsperado.compareTo(valorFinalCalculado), 0, "O valor não deve mudar com desconto de 0%.");
    }

    @Test
    @DisplayName("RF003.02 - Se o desconto for de 100%, o valor final do pedido deve ser zero.")
    void deveZerarValor_quandoDescontoForCemPorCento() {
        // Dado: um pedido com valor total de 100.00 e um desconto de 100%
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(2);

        Pedido pedido = new Pedido();
        pedido.setItens(List.of(item));

        BigDecimal percentualDesconto = BigDecimal.ONE; // 100% (representado como 1.0)
        BigDecimal valorFinalEsperado = BigDecimal.ZERO; // 100.00 - (100.00 * 1.0) = 0.00

        // Quando: aplicarDescontoPercentual é chamado
        BigDecimal valorFinalCalculado = pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto);

        // Então: o valor final do pedido deve ser zero
        assertEquals(valorFinalEsperado.compareTo(valorFinalCalculado), 0, "O valor deve ser zero com desconto de 100%.");
    }

    @Test
    @DisplayName("RF003.02 - Deve avisar com um erro se o percentual de desconto for negativo.")
    void deveLancarErro_quandoPercentualDescontoNegativo() {
        // Dado: um pedido e um percentual de desconto negativo
        Pedido pedido = new Pedido();

        ItemPedido itemMock = new ItemPedido();
        itemMock.setProduto(new Produto("Mock", "Mock Product", TipoProduto.OUTROS, BigDecimal.ONE));
        itemMock.setQuantidade(1);
        pedido.setItens(List.of(itemMock));

        BigDecimal percentualDesconto = new BigDecimal("-0.05"); // -5%

        // Quando: aplicarDescontoPercentual é chamado com percentual negativo
        // Então: uma IllegalArgumentException deve ser lançada
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto),
                "Deve lançar IllegalArgumentException para percentual negativo."
        );
        assertEquals("O percentual de desconto não pode ser negativo.", thrown.getMessage());
    }

    @Test
    @DisplayName("RF003.02 - Deve avisar com um erro se o percentual de desconto for maior que 100%.")
    void deveLancarErro_quandoPercentualDescontoMaiorQueCemPorCento() {
        // Dado: um pedido e um percentual de desconto maior que 100%
        Pedido pedido = new Pedido();
        ItemPedido itemMock = new ItemPedido();
        itemMock.setProduto(new Produto("Mock", "Mock Product", TipoProduto.OUTROS, BigDecimal.ONE));
        itemMock.setQuantidade(1);
        pedido.setItens(List.of(itemMock));

        BigDecimal percentualDesconto = new BigDecimal("1.01"); // 101%

        // Quando: aplicarDescontoPercentual é chamado com percentual > 100%
        // Então: uma IllegalArgumentException deve ser lançada
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto),
                "Deve lançar IllegalArgumentException para percentual maior que 100%."
        );
        assertEquals("O percentual de desconto não pode ser maior que 100%.", thrown.getMessage());
    }

    @Test
    @DisplayName("RF003.02 - Deve avisar com um erro se o percentual de desconto for nulo.")
    void deveLancarErro_quandoPercentualDescontoNulo() {
        // Dado: um pedido e um percentual de desconto nulo
        Pedido pedido = new Pedido();
        ItemPedido itemMock = new ItemPedido();
        itemMock.setProduto(new Produto("Mock", "Mock Product", TipoProduto.OUTROS, BigDecimal.ONE));
        itemMock.setQuantidade(1);
        pedido.setItens(List.of(itemMock));

        BigDecimal percentualDesconto = null;

        // Quando: aplicarDescontoPercentual é chamado com percentual nulo
        // Então: uma IllegalArgumentException deve ser lançada
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoService.aplicarDescontoPercentual(pedido, percentualDesconto),
                "Deve lançar IllegalArgumentException para percentual nulo."
        );
        assertEquals("O percentual de desconto não pode ser nulo.", thrown.getMessage());
    }

    // --- RF003.03: Validação Básica de Pedido ---

    @Test
    @DisplayName("RF003.03 - Um pedido deve ser considerado válido quando todas as suas informações estão corretas.")
    void deveSerValido_quandoTodasInformacoesCorretas() {
        // Dado: um pedido com nome de cliente e lista de itens válidos
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setCliente("João da Silva");
        pedido.setItens(List.of(item));

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar true
        assertTrue(isValid, "O pedido com todas as informações corretas deve ser válido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido nulo deve ser considerado inválido.")
    void deveSerInvalido_quandoPedidoNulo() {
        // Dado: um pedido nulo
        Pedido pedidoNulo = null;

        // Quando: validarPedido é chamado com pedido nulo
        boolean isValid = pedidoService.validarPedido(pedidoNulo);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido nulo deve ser inválido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido sem nome de cliente (nulo) deve ser considerado inválido.")
    void deveSerInvalido_quandoNomeClienteNulo() {
        // Dado: um pedido com nome de cliente nulo
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setCliente(null); // Nome do cliente nulo
        pedido.setItens(List.of(item));

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido com nome de cliente nulo deve ser inválido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido com nome de cliente vazio deve ser considerado inválido.")
    void deveSerInvalido_quandoNomeClienteVazio() {
        // Dado: um pedido com nome de cliente vazio
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setCliente(""); // Nome do cliente vazio
        pedido.setItens(List.of(item));

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido com nome de cliente vazio deve ser inválido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido com nome de cliente apenas com espaços deve ser considerado inválido.")
    void deveSerInvalido_quandoNomeClienteApenasEspacos() {
        // Dado: um pedido com nome de cliente apenas com espaços
        ItemPedido item = new ItemPedido();
        item.setProduto(produto1);
        item.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setCliente("   "); // Nome do cliente com espaços
        pedido.setItens(List.of(item));

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido com nome de cliente apenas com espaços deve ser inválido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido sem lista de itens (nula) deve ser considerado inválido.")
    void deveSerInvalido_quandoListaDeItensNula() {
        // Dado: um pedido com lista de itens nula
        Pedido pedido = new Pedido();
        pedido.setCliente("Maria Souza");
        pedido.setItens(null); // Lista de itens nula

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido com lista de itens nula deve ser inválido.");
    }

    @Test
    @DisplayName("RF003.03 - Um pedido com a lista de itens vazia deve ser considerado inválido.")
    void deveSerInvalido_quandoListaDeItensVazia() {
        // Dado: um pedido com lista de itens vazia
        Pedido pedido = new Pedido();
        pedido.setCliente("Carlos Lima");
        pedido.setItens(new ArrayList<>()); // Lista de itens vazia

        // Quando: validarPedido é chamado
        boolean isValid = pedidoService.validarPedido(pedido);

        // Então: deve retornar false
        assertFalse(isValid, "Um pedido com lista de itens vazia deve ser inválido.");
    }
}