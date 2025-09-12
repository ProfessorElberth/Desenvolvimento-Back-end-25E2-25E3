package br.edu.infnet.pedidosapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.pedidosapi.auxiliar.PedidoHelper;
import br.edu.infnet.pedidosapi.interfaces.AutorizadorPedidoService;
import br.edu.infnet.pedidosapi.model.domain.Pedido;
import br.edu.infnet.pedidosapi.model.exceptios.PedidoNaoAutorizadoException;
import br.edu.infnet.pedidosapi.model.service.GestaoPedidoService;

public class GestaoPedidoServiceTest {

    @Test
    @DisplayName("Deve incluir pedido quando o serviço de autorização retorna true")
    void deveIncluirPedido_quandoAutorizado() {
        AutorizadorPedidoService autorizadorMock = mock(AutorizadorPedidoService.class);
        when(autorizadorMock.autorizar(any(Pedido.class))).thenReturn(true);

        GestaoPedidoService service = new GestaoPedidoService(autorizadorMock);
        Pedido pedido = PedidoHelper.newPedido(1L);

        assertDoesNotThrow(() -> service.incluir(pedido));

        verify(autorizadorMock, times(1)).autorizar(pedido);
        assertEquals(1L, pedido.getId(), "O ID do pedido deve ser 1L após a inclusão.");
    }

    @Test
    @DisplayName("Deve lançar PedidoNaoAutorizadoException quando o serviço de autorização retorna false")
    void deveLancarException_quandoNaoAutorizado() {
        AutorizadorPedidoService autorizadorMock = mock(AutorizadorPedidoService.class);
        when(autorizadorMock.autorizar(any(Pedido.class))).thenReturn(false);

        GestaoPedidoService service = new GestaoPedidoService(autorizadorMock);
        Pedido pedido = PedidoHelper.newPedido(2L);
        String mensagemException = "O pedido não foi autorizado!";

        PedidoNaoAutorizadoException exception = assertThrows(PedidoNaoAutorizadoException.class, () -> service.incluir(pedido));

        assertEquals(mensagemException, exception.getMessage());

        verify(autorizadorMock, times(1)).autorizar(pedido);
    }
}