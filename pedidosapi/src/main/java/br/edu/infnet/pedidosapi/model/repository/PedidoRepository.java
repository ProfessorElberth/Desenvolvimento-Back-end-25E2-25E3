package br.edu.infnet.pedidosapi.model.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import br.edu.infnet.pedidosapi.model.domain.Pedido;

public class PedidoRepository {

	private final Map<Long, Pedido> mapa = new HashMap<Long, Pedido>();
	private final AtomicLong nextId = new AtomicLong(1);

	public Pedido incluir(Pedido pedido) {

		pedido.setId(nextId.getAndIncrement());

		mapa.put(pedido.getId(), pedido);

		return pedido;
	}

	public Optional<Pedido> obterPorId(Long id) {
		return Optional.ofNullable(mapa.get(id));
	}

	public void clear() {
		mapa.clear();
		nextId.set(1);
	}

}