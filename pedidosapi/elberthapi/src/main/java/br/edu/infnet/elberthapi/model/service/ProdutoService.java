package br.edu.infnet.elberthapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService implements CrudService<Produto, Integer> {

	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	@Transactional
	public Produto incluir(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto alterar(Integer id, Produto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto obterPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Produto> obterLista() {
		return produtoRepository.findAll();
	}

}
