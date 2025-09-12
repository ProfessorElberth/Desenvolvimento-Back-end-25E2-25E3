package br.edu.infnet.pedidosapi.interfaces;

import java.util.Optional;

import br.edu.infnet.pedidosapi.model.domain.Produto;

public interface ProdutoRepository {
	Optional<Produto> findById(Long id);
}