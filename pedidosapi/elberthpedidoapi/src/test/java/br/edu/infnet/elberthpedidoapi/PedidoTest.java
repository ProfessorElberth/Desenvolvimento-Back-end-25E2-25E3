package br.edu.infnet.elberthpedidoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.elberthpedidoapi.model.domain.ItemPedido;
import br.edu.infnet.elberthpedidoapi.model.domain.Pedido;
import br.edu.infnet.elberthpedidoapi.model.domain.StatusPedido;

public class PedidoTest {

	@Test
    @DisplayName("Deve inicializar Pedido com data de criação, status PENDENTE e número gerado automaticamente.")
    void deveInicializarPedidoComValoresPadrao() {
        // Dado: um novo Pedido é instanciado
        LocalDateTime antesCriacao = LocalDateTime.now().minusSeconds(1);
        Pedido pedido = new Pedido();
        LocalDateTime depoisCriacao = LocalDateTime.now().plusSeconds(1);

        // Quando: os getters são chamados
        String numero = pedido.getNumero();
        LocalDateTime dataCriacao = pedido.getDataCriacao();
        StatusPedido status = pedido.getStatus();
        String cliente = pedido.getCliente();

        // Então: os valores devem estar corretamente inicializados
        assertNotNull(numero, "O número do pedido não deve ser nulo.");
        assertTrue(isValidUUID(numero), "O número do pedido deve ser um UUID válido.");

        assertNotNull(dataCriacao, "A data de criação não deve ser nula.");
        assertTrue(dataCriacao.isAfter(antesCriacao), "A data de criação não deve ser anterior ao momento da criação do pedido.");
        assertTrue(dataCriacao.isBefore(depoisCriacao), "A data de criação não deve ser posterior ao momento da criação do pedido.");


        assertEquals(StatusPedido.PENDENDE, status, "O status inicial do pedido deve ser PENDENTE.");

        assertEquals(null, cliente, "O nome do cliente deve ser nulo por padrão.");

        assertNotNull(pedido.getItens(), "A lista de itens não deve ser nula.");
        assertTrue(pedido.getItens().isEmpty(), "A lista de itens deve ser vazia por padrão.");
    }

    private boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Test
    @DisplayName("Deve permitir definir e obter o nome do cliente.")
    void devePermitirDefinirEObterNomeCliente() {
        // Dado: um novo Pedido
        Pedido pedido = new Pedido();
        String nomeClienteEsperado = "Novo Cliente Teste";

        // Quando: o nome do cliente é definido
        pedido.setCliente(nomeClienteEsperado);

        // Então: o getter deve retornar o nome definido
        assertEquals(nomeClienteEsperado, pedido.getCliente(), "O nome do cliente deve ser o valor definido.");
    }

    @Test
    @DisplayName("Deve permitir definir e obter o status do pedido.")
    void devePermitirDefinirEObterStatusPedido() {
        // Dado: um novo Pedido
        Pedido pedido = new Pedido();
        StatusPedido statusEsperado = StatusPedido.PROCESSANDO;

        // Quando: o status do pedido é definido
        pedido.setStatus(statusEsperado);

        // Então: o getter deve retornar o status definido
        assertEquals(statusEsperado, pedido.getStatus(), "O status do pedido deve ser o valor definido.");
    }

    @Test
    @DisplayName("Deve permitir definir e obter a lista de itens do pedido.")
    void devePermitirDefinirEObterItensPedido() {
        // Dado: um novo Pedido
        Pedido pedido = new Pedido();
        List<ItemPedido> novosItens = new ArrayList<>();
        novosItens.add(new ItemPedido());

        // Quando: a lista de itens é definida
        pedido.setItens(novosItens);

        // Então: o getter deve retornar a lista definida
        assertEquals(novosItens, pedido.getItens(), "A lista de itens do pedido deve ser a lista definida.");
        assertFalse(pedido.getItens().isEmpty(), "A lista de itens não deve estar vazia após a definição.");
    }

    @Test
    @DisplayName("deveRetornarFalse_quandoUUIDInvalido: Garante que isValidUUID retorne false para strings que não são UUIDs válidos.")
    void deveRetornarFalse_quandoUUIDInvalido() {
        // Dado: Uma string que claramente NÃO é um UUID válido
        String invalidUuidString = "esta-nao-e-um-uuid-valido";

        // Quando: o método isValidUUID é chamado com uma string inválida
        // O método isValidUUID é privado e está na mesma classe de teste, então podemos chamá-lo diretamente
        boolean resultado = isValidUUID(invalidUuidString);

        // Então: deve retornar false, cobrindo o bloco catch
        assertFalse(resultado, "isValidUUID deve retornar false para strings inválidas.");
    }

}