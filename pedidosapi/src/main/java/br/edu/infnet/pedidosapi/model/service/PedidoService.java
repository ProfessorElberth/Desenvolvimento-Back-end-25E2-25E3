package br.edu.infnet.pedidosapi.model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import br.edu.infnet.pedidosapi.interfaces.DescontoService;
import br.edu.infnet.pedidosapi.interfaces.GatewayPagamento;
import br.edu.infnet.pedidosapi.model.domain.Cliente;
import br.edu.infnet.pedidosapi.model.domain.ItemPedido;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.domain.Produto;
import br.edu.infnet.pedidosapi.model.exceptios.PagamentoFalhouException;
import br.edu.infnet.pedidosapi.model.repository.ProdutoRepositoryFake;

public class PedidoService {

    private final DescontoService descontoService;
    private final SpyAuditoriaService auditoriaServiceSpy;
    private final GatewayPagamento gatewayPagamentoMock;
    private final ProdutoRepositoryFake produtoRepositoryFake;

    public PedidoService(DescontoService descontoService, SpyAuditoriaService auditoriaServiceSpy,
			GatewayPagamento gatewayPagamentoMock, ProdutoRepositoryFake produtoRepositoryFake) {
        this.descontoService = descontoService;
        this.auditoriaServiceSpy = auditoriaServiceSpy;
        this.gatewayPagamentoMock = gatewayPagamentoMock;
        this.produtoRepositoryFake = produtoRepositoryFake;
	}

	public Pedido incluir(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo para inclusão.");
        }
        auditoriaServiceSpy.registrarEvento("Inclusão de um pedido", "ID: " + pedido.getId());
        return pedido;
    }

    public BigDecimal calcularSubtotal(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo para cálculo de subtotal.");
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        List<ItemPedido> itens = pedido.getItens();

        if (itens == null || itens.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        for (ItemPedido item : itens) {
            if (item == null) {
            	auditoriaServiceSpy.registrarEvento("Erro", "Item de pedido nulo encontrado no cálculo de subtotal.");
                continue;
            }
            if (item.getProduto() == null) {
                throw new IllegalArgumentException("Produto do item não pode ser nulo.");
            }

            Optional<Produto> produtoOpt = produtoRepositoryFake.findById(item.getProduto().getId());
            if (produtoOpt.isEmpty()) {
            	auditoriaServiceSpy.registrarEvento("Erro", "Produto com ID " + item.getProduto().getId() + " não encontrado no repositório.");
                continue;
            }
            Produto produto = produtoOpt.get();

            if (produto.getPreco() == null) {
                throw new IllegalArgumentException("Preço unitário do produto '" + produto.getNome() + "' não pode ser nulo.");
            }
            if (item.getQuantidade() <= 0) {
                continue;
            }

            BigDecimal valorItem = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            subtotal = subtotal.add(valorItem);
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularValorTotalComDesconto(Pedido pedido, Cliente cliente) {
        BigDecimal subtotal = calcularSubtotal(pedido);

        if (subtotal.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal valorTotalComDesconto = BigDecimal.ZERO;
        for (ItemPedido item : pedido.getItens()) {
            if (item == null || item.getProduto() == null || item.getQuantidade() <= 0) {
                continue;
            }

            Optional<Produto> produtoOpt = produtoRepositoryFake.findById(item.getProduto().getId());
            if (produtoOpt.isEmpty()) {
                continue;
            }
            Produto produto = produtoOpt.get();

            if (produto.getPreco() == null) {
                continue;
            }

            BigDecimal valorItemOriginal = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            BigDecimal percentualDesconto = descontoService.obterPercentualDesconto(item);
            BigDecimal descontoAplicado = valorItemOriginal.multiply(percentualDesconto);
            valorTotalComDesconto = valorTotalComDesconto.add(valorItemOriginal.subtract(descontoAplicado));
        }

        BigDecimal descontoMaximoPermitido = subtotal.multiply(new BigDecimal("0.50"));
        BigDecimal valorMinimoAceitavel = subtotal.subtract(descontoMaximoPermitido);

        if (valorTotalComDesconto.compareTo(valorMinimoAceitavel) < 0) {
            valorTotalComDesconto = valorMinimoAceitavel;
            auditoriaServiceSpy.registrarEvento("Desconto Limitado",
                    "Desconto do pedido ID " + pedido.getId() + " limitado a 50% do subtotal.");
        }

        return valorTotalComDesconto.setScale(2, RoundingMode.HALF_UP);
    }

    public Pedido finalizarPedido(Pedido pedido, Cliente cliente) throws PagamentoFalhouException {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo para processamento.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo para processamento de pedido.");
        }

        auditoriaServiceSpy.registrarEvento("Iniciando processamento do pedido", "ID: " + pedido.getId());

        BigDecimal valorFinal = calcularValorTotalComDesconto(pedido, cliente);

        boolean pagamentoSucesso = false;
        try {
            pagamentoSucesso = gatewayPagamentoMock.processarPagamento(valorFinal, cliente);
        } catch (PagamentoFalhouException e) {
            auditoriaServiceSpy.registrarEvento("Falha no Pagamento",
                    "Pedido ID " + pedido.getId() + " - " + e.getMessage());
            throw e;
        }

        if (!pagamentoSucesso) {
            auditoriaServiceSpy.registrarEvento("Pagamento Recusado",
                    "Pedido ID " + pedido.getId() + " - Pagamento não autorizado.");
            throw new PagamentoFalhouException("Pagamento não autorizado para o pedido " + pedido.getId());
        }

        auditoriaServiceSpy.registrarEvento("Pedido Finalizado",
                "Pedido ID " + pedido.getId() + " finalizado com sucesso. Valor: " + valorFinal);

        return pedido;
    }
}