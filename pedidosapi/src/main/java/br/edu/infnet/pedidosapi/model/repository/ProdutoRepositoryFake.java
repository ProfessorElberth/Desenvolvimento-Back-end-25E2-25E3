package br.edu.infnet.pedidosapi.model.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.edu.infnet.pedidosapi.interfaces.ProdutoRepository;
import br.edu.infnet.pedidosapi.model.domain.Produto;

public class ProdutoRepositoryFake implements ProdutoRepository {

    private final Map<Long, Produto> produtos = new HashMap<>();

    public ProdutoRepositoryFake() {
        produtos.put(1L, new Produto(1L, "Notebook Gamer", new BigDecimal("5000.00")));
        produtos.put(2L, new Produto(2L, "Mouse Sem Fio", new BigDecimal("150.00")));
        produtos.put(3L, new Produto(3L, "Teclado Mecânico", new BigDecimal("400.00")));
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(produtos.get(id));
    }

    public void addProduto(Produto produto) {
        produtos.put(produto.getId(), produto);
    }

    public void clear() {
        produtos.clear();
    }
}