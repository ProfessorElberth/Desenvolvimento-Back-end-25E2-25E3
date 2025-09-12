package br.edu.infnet.filmeapi.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.filmeapi.model.domain.Filme;

@Service
public class FilmeService {

	private final Map<Integer, Filme> mapaFilme = new HashMap<Integer, Filme>();
	private final AtomicInteger nextId = new AtomicInteger(1); 

	public Filme incluir(Filme filme){
		
		filme.setId(nextId.getAndIncrement());		

		mapaFilme.put(filme.getId(), filme);
		
		return filme;
	}
	
	public List<Filme> obterLista(){

		return new ArrayList<Filme>(mapaFilme.values());
	}
}