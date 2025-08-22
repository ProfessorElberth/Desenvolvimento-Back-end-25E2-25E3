package br.edu.infnet.tarefaapi.model.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TarefaService {
	
	private final Map<Long, Tarefa> mapa = new ConcurrentHashMap<Long, Tarefa>();
	private final AtomicLong nextId = new AtomicLong(1);
	
	public Tarefa incluir(String titulo, String descricao){
		
		Long id = getId();
				
		Tarefa tarefa = new Tarefa(id, titulo, descricao);
		
		mapa.put(id, tarefa);

		return tarefa;
	}
	
	public Tarefa alterar(Long id, String titulo, String descricao){
		
		//TODO Validações
		
		Tarefa tarefa = mapa.get(id);

		//TODO Validar se tarefa foi encontrada

		tarefa.setTitulo(titulo);
		tarefa.setDescricao(descricao);
		
		return tarefa;
	}
	
	private Long getId() {
		return nextId.getAndIncrement();
	}
}