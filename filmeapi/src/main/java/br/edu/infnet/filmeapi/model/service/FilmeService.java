package br.edu.infnet.filmeapi.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.filmeapi.auxiliares.DadosInvalidosException;
import br.edu.infnet.filmeapi.auxiliares.FilmeNaoEncontradoException;
import br.edu.infnet.filmeapi.model.domain.Filme;

@Service
public class FilmeService {

	private final Map<Integer, Filme> mapaFilme = new HashMap<Integer, Filme>();
	private final AtomicInteger nextId = new AtomicInteger(1); 

	private void validar(Filme filme) {
		
		if(filme.getTitulo() == null) {
			throw new DadosInvalidosException("O título do filme está nulo");
		}
		
	}

	public Filme incluir(Filme filme){
		
		validar(filme);
		
		filme.setId(nextId.getAndIncrement());		

		mapaFilme.put(filme.getId(), filme);
		
		return filme;
	}
	
	public List<Filme> obterLista(){

		return new ArrayList<Filme>(mapaFilme.values());
	}

	public void excluir(Integer id) {
		
		if(!mapaFilme.containsKey(id)) {
			throw new FilmeNaoEncontradoException("Filme não encontrado para o ID " + id);
		}
		
		mapaFilme.remove(id);
	}

	public Filme alterar(Integer id, Filme filmeAlterado) {
		
		if(!mapaFilme.containsKey(id)) {
			throw new FilmeNaoEncontradoException("Filme não encontrado para o ID " + id);
		}

		validar(filmeAlterado);

		filmeAlterado.setId(id);		

		mapaFilme.put(filmeAlterado.getId(), filmeAlterado);
		
		return filmeAlterado;
	}
	
}