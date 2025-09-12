package br.edu.infnet.pedidosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.infnet.pedidosapi.auxiliar.PedidoHelper;
import br.edu.infnet.pedidosapi.interfaces.DescontoService;
import br.edu.infnet.pedidosapi.interfaces.GatewayPagamento;
import br.edu.infnet.pedidosapi.model.domain.Cliente;
import br.edu.infnet.pedidosapi.model.domain.ItemPedido;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.domain.Produto;
import br.edu.infnet.pedidosapi.model.exceptios.PagamentoFalhouException;
import br.edu.infnet.pedidosapi.model.repository.PedidoRepository;
import br.edu.infnet.pedidosapi.model.repository.ProdutoRepositoryFake;
import br.edu.infnet.pedidosapi.model.service.PedidoService;
import br.edu.infnet.pedidosapi.model.service.SpyAuditoriaService;
import br.edu.infnet.pedidosapi.model.service.StubDescontoServiceDez;
import br.edu.infnet.pedidosapi.model.service.StubDescontoServiceZero;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    private DescontoService descontoService;
    private SpyAuditoriaService auditoriaServiceSpy;
    @Mock
    private GatewayPagamento gatewayPagamentoMock;
    private ProdutoRepositoryFake produtoRepositoryFake;
    @Mock
    private PedidoRepository pedidoRepositoryMock;

    private PedidoService pedidoService;
    private Cliente clienteDummy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteDummy = new Cliente(1L, "Dummy Cliente");

        descontoService = new StubDescontoServiceZero();
        auditoriaServiceSpy = new SpyAuditoriaService();
        produtoRepositoryFake = new ProdutoRepositoryFake();

        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        produtoRepositoryFake.clear();
        produtoRepositoryFake.addProduto(PedidoHelper.newProduto(1L, "Produto Teste A", 100.00));
        produtoRepositoryFake.addProduto(PedidoHelper.newProduto(2L, "Produto Teste B", 50.00));
        produtoRepositoryFake.addProduto(PedidoHelper.newProduto(3L, "Produto Teste C", 20.00));
        produtoRepositoryFake.addProduto(PedidoHelper.newProduto(4L, "Produto Caro", 2000.00));

        auditoriaServiceSpy.reset();
    }

    // --- Testes para PedidoService.incluir ---
    @Test
    @DisplayName("Deve registrar um evento de auditoria quando um pedido é incluído")
    void deveRegistrarEventoAuditoria_quandoIncluirPedido() {
        Pedido pedido = PedidoHelper.newPedido(1L);

        pedidoService.incluir(pedido);

        assertTrue(auditoriaServiceSpy.isFoiChamado(), "O spy deve ter registrado a chamada do método de inclusão.");
        assertEquals("Inclusão de um pedido", auditoriaServiceSpy.getEvento());
        assertEquals("ID: 1", auditoriaServiceSpy.getDetalhes());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o pedido for nulo na inclusão")
    void deveLancarExcecao_quandoIncluirPedidoNulo() {
        Pedido pedidoNulo = null;

        assertThrows(IllegalArgumentException.class, () -> pedidoService.incluir(pedidoNulo),
                "Deveria lançar IllegalArgumentException para pedido nulo na inclusão.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada quando o pedido é nulo.");
    }

    // --- Testes para PedidoService.calcularSubtotal ---
    @Test
    @DisplayName("Deve calcular o subtotal corretamente para um pedido com itens válidos")
    void deveCalcularSubtotalCorretamenteComItensValidos() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 2); // Preço do P1 é 100.00
        ItemPedido i2 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(2L, "Produto Teste B", 0.0), 3); // Preço do P2 é 50.00
        Pedido pedido = PedidoHelper.newPedido(1L, i1, i2);

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedido);

        // Total = (100.00 * 2) + (50.00 * 3) = 200.00 + 150.00 = 350.00
        assertEquals(new BigDecimal("350.00").setScale(2, RoundingMode.HALF_UP), subtotal,
                "O subtotal deveria ser 350.00 para os itens fornecidos.");
    }

    @Test
    @DisplayName("Deve retornar zero para o subtotal de um pedido vazio")
    void deveRetornarZeroParaPedidoVazioNoSubtotal() {
        Pedido pedidoVazio = PedidoHelper.newPedido(2L);

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedidoVazio);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), subtotal,
                "O subtotal para pedido vazio deveria ser 0.00.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada para pedido vazio.");
    }

    @Test
    @DisplayName("Deve ignorar itens com quantidade zero ao calcular o subtotal")
    void deveIgnorarItensComQuantidadeZeroNoSubtotal() {
        // Produto 1L tem preço 100.00
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 2); // Válido: 100.00 * 2 = 200.00
        ItemPedido i2 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(2L, "Produto Teste B", 0.0), 0);  // Quantidade zero, deve ser ignorado
        Pedido pedido = PedidoHelper.newPedido(3L, i1, i2);

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedido);

        // Apenas i1 deve ser contado: 200.00
        assertEquals(new BigDecimal("200.00").setScale(2, RoundingMode.HALF_UP), subtotal,
                "Apenas itens com quantidade positiva deveriam ser incluídos no subtotal.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada ao ignorar itens inválidos.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para Pedido nulo ao calcular subtotal")
    void deveLancarIllegalArgumentExceptionParaPedidoNuloEmCalcularSubtotal() {
        Pedido pedidoNulo = null;

        assertThrows(IllegalArgumentException.class, () -> pedidoService.calcularSubtotal(pedidoNulo),
                "Deveria lançar IllegalArgumentException para pedido nulo.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada para pedido nulo no cálculo de subtotal.");
    }

    @Test
    @DisplayName("Deve retornar zero para o subtotal quando a lista de itens é nula")
    void deveRetornarZeroQuandoListaDeItensEhNula() {
        Pedido pedidoComItensNulos = new Pedido(1L, null); // Construtor de Pedido deve lidar com null

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedidoComItensNulos);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), subtotal,
                "O subtotal para pedido com lista de itens nula deveria ser zero.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada para lista de itens nula.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para item com produto nulo")
    void deveLancarIllegalArgumentExceptionParaItemComProdutoNulo() {
        ItemPedido itemComProdutoNulo = new ItemPedido(null, 1);
        Pedido pedido = PedidoHelper.newPedido(1L, itemComProdutoNulo);

        assertThrows(IllegalArgumentException.class, () -> pedidoService.calcularSubtotal(pedido),
                "Deveria lançar IllegalArgumentException para item com produto nulo.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada para item com produto nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para item com preço de produto nulo")
    void deveLancarIllegalArgumentExceptionParaItemComPrecoNulo() {
        Produto produtoSemPreco = new Produto(1L, "Produto sem Preço", null);
        produtoRepositoryFake.addProduto(produtoSemPreco); // Adiciona ao fake para ser encontrado
        ItemPedido itemComPrecoNulo = PedidoHelper.newItemPedido(produtoSemPreco, 1);
        Pedido pedido = PedidoHelper.newPedido(1L, itemComPrecoNulo);

        assertThrows(IllegalArgumentException.class, () -> pedidoService.calcularSubtotal(pedido),
                "Deveria lançar IllegalArgumentException para item com preço unitário nulo.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada para item com preço nulo.");
    }

    @Test
    @DisplayName("Deve auditar erro quando produto não é encontrado no repositório")
    void deveAuditarErroQuandoProdutoNaoEncontradoNoRepositorio() {
        // ID 99L não existe no produtoRepositoryFake
        ItemPedido itemProdutoInexistente = PedidoHelper.newItemPedido(PedidoHelper.newProduto(99L, "Produto Inexistente", 10.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, itemProdutoInexistente);

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedido);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), subtotal, "Subtotal deveria ser zero se o produto não for encontrado.");
        assertTrue(auditoriaServiceSpy.isFoiChamado(), "Deveria registrar evento de erro.");
        assertEquals("Erro", auditoriaServiceSpy.getEvento()); // Retorna o último evento
        assertTrue(auditoriaServiceSpy.getDetalhes().contains("Produto com ID 99 não encontrado"), "Detalhes devem indicar produto não encontrado.");
    }

    @Test
    @DisplayName("Deve ignorar itens nulos na lista de itens ao calcular subtotal e auditar")
    void deveIgnorarItensNulosNaListaEAuditar() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto A", 0.0), 2); // 100.00 * 2 = 200.00
        ItemPedido iNulo = null;
        ItemPedido i2 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(2L, "Produto B", 0.0), 1); // 50.00 * 1 = 50.00
        Pedido pedido = new Pedido(1L, Arrays.asList(i1, iNulo, i2));

        BigDecimal subtotal = pedidoService.calcularSubtotal(pedido);

        assertEquals(new BigDecimal("250.00").setScale(2, RoundingMode.HALF_UP), subtotal, "Subtotal deve somar apenas itens válidos.");
        assertTrue(auditoriaServiceSpy.isFoiChamado(), "Deveria auditar item nulo.");
        assertEquals("Erro", auditoriaServiceSpy.getEvento()); // Retorna o último evento
        assertTrue(auditoriaServiceSpy.getDetalhes().contains("Item de pedido nulo encontrado"), "Mensagem de auditoria deve indicar item nulo.");
    }

    // --- NOVO TESTE para PedidoHelper.newItemPedido ---
    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao criar ItemPedido com quantidade negativa")
    void newItemPedido_deveLancarExcecaoParaQuantidadeNegativa() {
        Produto produtoDummy = PedidoHelper.newProduto(1L, "Teste Produto", 10.0);
        assertThrows(IllegalArgumentException.class, () ->
            PedidoHelper.newItemPedido(produtoDummy, -5),
            "Deveria lançar IllegalArgumentException para quantidade negativa."
        );
    }

    // --- Testes para PedidoService.calcularValorTotalComDesconto ---
    @Test
    @DisplayName("Deve aplicar o desconto corretamente quando dentro do limite de 50%")
    void deveAplicarDescontoCorretamenteDentroDoLimite() {
        this.descontoService = new StubDescontoServiceDez();
        // Re-instanciar PedidoService com o DescontoService correto e o PedidoRepositoryMock
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, i1);

        BigDecimal totalComDesconto = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        assertEquals(new BigDecimal("90.00").setScale(2, RoundingMode.HALF_UP), totalComDesconto,
                "Deveria aplicar o desconto de 10.00 corretamente.");
        assertFalse(auditoriaServiceSpy.getEventosRegistrados().contains("Desconto Limitado"),
                "A auditoria não deveria registrar 'Desconto Limitado' neste caso.");
    }

    @Test
    @DisplayName("Deve limitar o desconto total a 50% do subtotal")
    void deveLimitarDescontoATetoDeCinquentaPorCento() {
        this.descontoService = (itemPedido) -> BigDecimal.ONE;
        // Re-instanciar PedidoService com o DescontoService correto e o PedidoRepositoryMock
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(4L, "Produto Caro", 0.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, i1);

        BigDecimal valorFinal = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        BigDecimal subtotalOriginal = new BigDecimal("2000.00");
        BigDecimal valorEsperadoComTeto = subtotalOriginal.subtract(subtotalOriginal.multiply(new BigDecimal("0.50")));
        assertEquals(valorEsperadoComTeto.setScale(2, RoundingMode.HALF_UP), valorFinal,
                "O desconto deveria ser limitado a 50%.");
        assertTrue(auditoriaServiceSpy.isFoiChamado(), "A auditoria deveria registrar o limite de desconto.");
        assertTrue(auditoriaServiceSpy.getDetalhes().contains("limitado a 50%"), "A mensagem de auditoria deve indicar o limite.");
        assertTrue(auditoriaServiceSpy.getEventosRegistrados().contains("Desconto Limitado"), "O evento 'Desconto Limitado' deve ser registrado.");
    }

    @Test
    @DisplayName("Deve retornar zero no total com desconto para pedido com subtotal zero")
    void deveRetornarZeroNoTotalComDescontoParaSubtotalZero() {
        Pedido pedidoVazio = PedidoHelper.newPedido(1L);

        BigDecimal total = pedidoService.calcularValorTotalComDesconto(pedidoVazio, clienteDummy);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), total, "Total com desconto deveria ser zero para subtotal zero.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada.");
    }

    // --- NOVOS TESTES para os 'continue' em calcularValorTotalComDesconto ---

    @Test
    @DisplayName("Deve ignorar item nulo na lista de itens ao calcular o valor total com desconto")
    void deveIgnorarItemNuloAoCalcularValorTotalComDesconto() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto A", 0.0), 2); // 100.00 * 2 = 200.00
        ItemPedido iNulo = null;
        Pedido pedido = new Pedido(1L, Arrays.asList(i1, iNulo));

        // Stub de desconto zero para simplificar o cálculo
        this.descontoService = new StubDescontoServiceZero();
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        BigDecimal totalComDesconto = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        assertEquals(new BigDecimal("200.00").setScale(2, RoundingMode.HALF_UP), totalComDesconto,
                "O total deve somar apenas itens válidos, ignorando o item nulo.");
        // Não há auditoria específica para item nulo dentro de calcularValorTotalComDesconto,
        // apenas em calcularSubtotal, mas neste caminho não chega lá, pois o continue é direto.
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ser chamada ao ignorar item nulo.");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para item com produto nulo ao calcular valor total com desconto (via subtotal)")
    void deveLancarIllegalArgumentExceptionParaItemComProdutoNuloAoCalcularValorTotalComDesconto() {
        // Criar um pedido com um item cujo produto é nulo
        ItemPedido itemComProdutoNulo = new ItemPedido(null, 1);
        Pedido pedido = PedidoHelper.newPedido(1L, itemComProdutoNulo); // Pedido com item inválido

        // O serviço deve lançar IllegalArgumentException, pois calcularSubtotal (primeira chamada) validará o produto nulo
        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);
        }, "Deveria lançar IllegalArgumentException quando um item com produto nulo é passado.");

        // Verificar que nenhuma auditoria foi registrada para este cenário de erro de validação inicial
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada em caso de validação inicial falha.");
    }


    @Test
    @DisplayName("Deve ignorar item com quantidade zero ou negativa ao calcular o valor total com desconto")
    void deveIgnorarItemComQuantidadeZeroOuNegativaAoCalcularValorTotalComDesconto() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto A", 0.0), 2); // 100.00 * 2 = 200.00
        ItemPedido iZero = PedidoHelper.newItemPedido(PedidoHelper.newProduto(2L, "Produto B", 0.0), 0); // Quantidade zero
        Pedido pedido = new Pedido(1L, Arrays.asList(i1, iZero));

        this.descontoService = new StubDescontoServiceZero();
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        BigDecimal totalComDesconto = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        assertEquals(new BigDecimal("200.00").setScale(2, RoundingMode.HALF_UP), totalComDesconto,
                "O total deve somar apenas itens válidos, ignorando item com quantidade zero.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ser chamada ao ignorar item com quantidade zero.");
    }

    @Test
    @DisplayName("Deve auditar e ignorar item com produto não encontrado no repositório ao calcular valor total com desconto") // Nome atualizado
    void deveAuditarEIgnorarItemComProdutoNaoEncontradoNoRepositorioAoCalcularValorTotalComDesconto() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto A", 0.0), 2); // 100.00 * 2 = 200.00
        ItemPedido itemProdutoInexistente = PedidoHelper.newItemPedido(PedidoHelper.newProduto(999L, "Inexistente", 0.0), 1); // ID 999 não existe
        Pedido pedido = new Pedido(1L, Arrays.asList(i1, itemProdutoInexistente));

        this.descontoService = new StubDescontoServiceZero();
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        BigDecimal totalComDesconto = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        assertEquals(new BigDecimal("200.00").setScale(2, RoundingMode.HALF_UP), totalComDesconto,
                "O total deve somar apenas itens válidos, ignorando item com produto não encontrado.");
        // A auditoria *deve* ser chamada por causa da lógica em calcularSubtotal
        assertTrue(auditoriaServiceSpy.isFoiChamado(), "A auditoria deveria ser chamada quando um produto não é encontrado.");
        assertEquals("Erro", auditoriaServiceSpy.getEvento());
        assertTrue(auditoriaServiceSpy.getDetalhes().contains("Produto com ID 999 não encontrado no repositório."),
                "Detalhes da auditoria devem indicar produto não encontrado.");
    }

    @Test
    @DisplayName("Deve ignorar item com preço de produto nulo ao calcular valor total com desconto")
    void deveIgnorarItemComPrecoDeProdutoNuloAoCalcularValorTotalComDesconto() {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto A", 0.0), 2); // 100.00 * 2 = 200.00
        Produto produtoComPrecoNulo = new Produto(5L, "Produto Sem Preço", null);
        produtoRepositoryFake.addProduto(produtoComPrecoNulo); // Adiciona ao fake
        ItemPedido itemComPrecoNulo = PedidoHelper.newItemPedido(produtoComPrecoNulo, 1);
        Pedido pedido = new Pedido(1L, Arrays.asList(i1, itemComPrecoNulo));

        this.descontoService = new StubDescontoServiceZero();
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        BigDecimal totalComDesconto = pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);

        assertEquals(new BigDecimal("200.00").setScale(2, RoundingMode.HALF_UP), totalComDesconto,
                "O total deve somar apenas itens válidos, ignorando item com preço de produto nulo.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ser chamada ao ignorar item com preço nulo.");
    }

    // --- Testes para PedidoService.finalizarPedido ---
    @Test
    @DisplayName("Deve processar um pedido completo com sucesso utilizando múltiplos dublês e salvar no repositório")
    void deveProcessarPedidoCompletoComSucesso() throws PagamentoFalhouException {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 2);
        ItemPedido i2 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(2L, "Produto Teste B", 0.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, i1, i2);

        this.descontoService = new StubDescontoServiceDez();
        pedidoService = new PedidoService(descontoService, auditoriaServiceSpy, gatewayPagamentoMock, produtoRepositoryFake);

        when(gatewayPagamentoMock.processarPagamento(any(BigDecimal.class), any(Cliente.class))).thenReturn(true);
        when(pedidoRepositoryMock.incluir(any(Pedido.class))).thenReturn(pedido);

        Pedido pedidoFinalizado = pedidoService.finalizarPedido(pedido, clienteDummy);

        pedidoService.calcularValorTotalComDesconto(pedido, clienteDummy);
        BigDecimal valorEsperadoFinal = new BigDecimal("225.00").setScale(2, RoundingMode.HALF_UP);


        org.mockito.Mockito.verify(gatewayPagamentoMock, org.mockito.Mockito.times(1))
                .processarPagamento(eq(valorEsperadoFinal), eq(clienteDummy));

        verify(pedidoRepositoryMock, times(1)).incluir(pedido);

        assertEquals(2, auditoriaServiceSpy.getEventosRegistrados().size(), "Deveriam ter sido registrados dois eventos de auditoria.");
        assertEquals("Iniciando processamento do pedido", auditoriaServiceSpy.getEventosRegistrados().get(0));
        assertEquals("ID: 1", auditoriaServiceSpy.getDetalhesRegistrados().get(0));
        assertEquals("Pedido Finalizado", auditoriaServiceSpy.getEventosRegistrados().get(1));

        String expectedSuccessDetails = "Pedido ID " + pedido.getId() + " finalizado com sucesso. Valor: " + valorEsperadoFinal;
        assertEquals(expectedSuccessDetails, auditoriaServiceSpy.getDetalhesRegistrados().get(1),
                "Os detalhes do segundo evento de auditoria devem ser os da finalização com sucesso.");

        assertEquals(pedido.getId(), pedidoFinalizado.getId(), "O ID do pedido retornado deve ser o mesmo.");
    }

    @Test
    @DisplayName("Deve lançar PagamentoFalhouException quando o gateway de pagamento falhar")
    void deveLancarExcecaoQuandoPagamentoFalhar() throws PagamentoFalhouException {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, i1);

        String mensagemErro = "Cartão recusado pelo emissor.";
        when(gatewayPagamentoMock.processarPagamento(any(BigDecimal.class), any(Cliente.class)))
                .thenThrow(new PagamentoFalhouException(mensagemErro));

        PagamentoFalhouException thrown = assertThrows(PagamentoFalhouException.class, () -> {
            pedidoService.finalizarPedido(pedido, clienteDummy);
        }, "Deveria lançar PagamentoFalhouException quando o pagamento falha.");

        assertEquals(mensagemErro, thrown.getMessage(), "A mensagem da exceção deve ser a do erro do gateway.");

        assertEquals(2, auditoriaServiceSpy.getEventosRegistrados().size(), "Deveriam ter sido registrados dois eventos de auditoria.");
        assertEquals("Iniciando processamento do pedido", auditoriaServiceSpy.getEventosRegistrados().get(0));
        assertEquals("ID: 1", auditoriaServiceSpy.getDetalhesRegistrados().get(0));
        assertEquals("Falha no Pagamento", auditoriaServiceSpy.getEventosRegistrados().get(1));
        assertTrue(auditoriaServiceSpy.getDetalhesRegistrados().get(1).contains(mensagemErro), "O segundo evento de auditoria deve conter a mensagem de erro.");
        assertFalse(auditoriaServiceSpy.getEventosRegistrados().contains("Pedido Finalizado"), "O evento 'Pedido Finalizado' não deveria ter sido registrado.");
        verify(pedidoRepositoryMock, times(0)).incluir(any(Pedido.class));
    }

    @Test
    @DisplayName("Deve lançar PagamentoFalhouException quando o gateway de pagamento retorna false")
    void deveLancarExcecaoQuandoGatewayRetornaFalse() throws PagamentoFalhouException {
        ItemPedido i1 = PedidoHelper.newItemPedido(PedidoHelper.newProduto(1L, "Produto Teste A", 0.0), 1);
        Pedido pedido = PedidoHelper.newPedido(1L, i1);

        when(gatewayPagamentoMock.processarPagamento(any(BigDecimal.class), any(Cliente.class)))
                .thenReturn(false);

        PagamentoFalhouException thrown = assertThrows(PagamentoFalhouException.class, () -> {
            pedidoService.finalizarPedido(pedido, clienteDummy);
        }, "Deveria lançar PagamentoFalhouException quando o gateway retorna false.");

        assertTrue(thrown.getMessage().contains("Pagamento não autorizado para o pedido"),
                "A mensagem da exceção deve indicar pagamento não autorizado.");

        assertEquals(2, auditoriaServiceSpy.getEventosRegistrados().size(), "Deveriam ter sido registrados dois eventos de auditoria.");
        assertEquals("Iniciando processamento do pedido", auditoriaServiceSpy.getEventosRegistrados().get(0));
        assertEquals("ID: 1", auditoriaServiceSpy.getDetalhesRegistrados().get(0));
        assertEquals("Pagamento Recusado", auditoriaServiceSpy.getEventosRegistrados().get(1));
        assertTrue(auditoriaServiceSpy.getDetalhesRegistrados().get(1).contains("Pagamento não autorizado."), "O segundo evento de auditoria deve indicar pagamento não autorizado.");
        assertFalse(auditoriaServiceSpy.getEventosRegistrados().contains("Pedido Finalizado"), "O evento 'Pedido Finalizado' não deveria ter sido registrado.");
        verify(pedidoRepositoryMock, times(0)).incluir(any(Pedido.class));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para Pedido nulo ao finalizar pedido")
    void deveLancarIllegalArgumentExceptionParaPedidoNuloAoFinalizarPedido() {
        Pedido pedidoNulo = null;

        assertThrows(IllegalArgumentException.class, () -> pedidoService.finalizarPedido(pedidoNulo, clienteDummy),
                "Deveria lançar IllegalArgumentException para pedido nulo ao finalizar.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada ao finalizar pedido nulo.");
        verify(pedidoRepositoryMock, times(0)).incluir(any(Pedido.class));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para Cliente nulo ao finalizar pedido")
    void deveLancarIllegalArgumentExceptionParaClienteNuloAoFinalizarPedido() {
        Pedido pedido = PedidoHelper.newPedido(1L);
        Cliente clienteNulo = null;

        assertThrows(IllegalArgumentException.class, () -> pedidoService.finalizarPedido(pedido, clienteNulo),
                "Deveria lançar IllegalArgumentException para cliente nulo ao finalizar.");
        assertFalse(auditoriaServiceSpy.isFoiChamado(), "A auditoria não deveria ter sido chamada ao finalizar pedido com cliente nulo.");
        verify(pedidoRepositoryMock, times(0)).incluir(any(Pedido.class));
    }
}