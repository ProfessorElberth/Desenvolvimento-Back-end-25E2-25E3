package br.edu.infnet.elberthpedidoapi.model.service;

import java.math.BigDecimal;
import java.util.Objects;

import br.edu.infnet.elberthpedidoapi.model.domain.ItemPedido;
import br.edu.infnet.elberthpedidoapi.model.domain.Pedido;

public class PedidoService {

    /**
     * RF003.01: Calcula o valor total de um pedido.
     * Retorna BigDecimal.ZERO se o pedido for nulo ou não tiver itens.
     */
    public BigDecimal calcularValorTotal(Pedido pedido) {
        if (Objects.isNull(pedido)) {
            return BigDecimal.ZERO;
        }

        if (Objects.isNull(pedido.getItens()) || pedido.getItens().isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : pedido.getItens()) {
            valorTotal = valorTotal.add(item.calcularSubTotal());
        }

        return valorTotal;
    }

    /**
     * RF003.02: Aplica um desconto percentual sobre o valor total do pedido.
     * Lança IllegalArgumentException para percentuais inválidos.
     */
    public BigDecimal aplicarDescontoPercentual(Pedido pedido, BigDecimal percentualDesconto) {
        BigDecimal valorTotal = calcularValorTotal(pedido);

        if (Objects.isNull(percentualDesconto)) {
            throw new IllegalArgumentException("O percentual de desconto não pode ser nulo.");
        }
        if (percentualDesconto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O percentual de desconto não pode ser negativo.");
        }
        if (percentualDesconto.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("O percentual de desconto não pode ser maior que 100%.");
        }

        BigDecimal valorDesconto = valorTotal.multiply(percentualDesconto);
        return valorTotal.subtract(valorDesconto);
    }

    /**
     * RF003.03: Valida a integridade básica de um pedido.
     * Retorna true se o pedido atende aos critérios mínimos de validade.
     */
    public boolean validarPedido(Pedido pedido) {

        if (Objects.isNull(pedido)) {
            return false;
        }

        if (Objects.isNull(pedido.getCliente()) || pedido.getCliente().trim().isEmpty()) {
            return false;
        }

        if (Objects.isNull(pedido.getItens()) || pedido.getItens().isEmpty()) {
            return false;
        }

        return true;
    }
}