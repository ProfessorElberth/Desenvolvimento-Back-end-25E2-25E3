package br.edu.infnet.tarefaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.infnet.tarefaapi.model.domain.StatusTarefa;
import br.edu.infnet.tarefaapi.model.domain.Tarefa;
import br.edu.infnet.tarefaapi.model.domain.TarefaService;

@SpringBootTest
@DisplayName("Testes para a classe TarefaService")
public class TarefaServiceTest {

	private TarefaService service;
	
	@BeforeEach
	void setUp() {
		service = new TarefaService();	
	}
	
	@Test
	@DisplayName("RF01 - Deve criar uma nova tarefa com Status PENDENTE e ID gerado automaticamente")
	void req01_deveCriarNovaTarefa_quandoParametrosPreenchidos_entaoTarefaCriadaCorretamente() {
		// Dado
		String titulo = "Estudar TDD";
		String descricao = "Aprender R-G-R";
		
		// Quando
		Tarefa novaTarefa = service.incluir(titulo, descricao);
		
		// Entao
		assertNotNull(novaTarefa, "A tarefa não deveria ser nula após a sua criação.");
		assertNotNull(novaTarefa.getId(), "O ID da tarefa não deveria ser nulo.");
		assertTrue(novaTarefa.getId() > 0, "O ID da tarefa deveria ser um número positivo.");
		assertEquals(titulo, novaTarefa.getTitulo(), "O título da tarefa deve ser o mesmo que o fornecido.");
		assertEquals(descricao, novaTarefa.getDescricao(), "A descrição da tarefa deve ser a mesma que a fornecida.");
		assertEquals(StatusTarefa.PENDENTE, novaTarefa.getStatus(), "O status inicial da tarefa deve ser PENDENTE");
		assertNotNull(novaTarefa.getDataCriacao(), "A data de criação não deveria estar nula.");
		assertNull(novaTarefa.getDataConclusao(), "A data de conclusão deveria ser nula para uma nova tarefa");
	}
	
	@Test
	@DisplayName("RF01a - Deve lançar IllegalArgumentException ao criar uma tarefa com título nulo.")
	void req01a_deveLancarExcecao_quandoCriarTarefaComTituloNulo() {
		//Dado
		String titulo = null;
		String descricao = "Aprender R-G-R";
		
		//Quando
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, 
				() -> service.incluir(titulo, descricao),
				"Deveria lançar uma IllegalArgumentException para o título nulo.");
		
		//Então
		assertEquals("O título da tarefa é obrigatório.", excecao.getMessage());
	}

	@Test
	@DisplayName("RF01b - Deve lançar IllegalArgumentException ao criar uma tarefa com título vazio.")
	void req01b_deveLancarExcecao_quandoCriarTarefaComTituloVazio() {
		//Dado
		String titulo = " ";
		String descricao = "Aprender R-G-R";
		
		//Quando
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, 
				() -> service.incluir(titulo, descricao),
				"Deveria lançar uma IllegalArgumentException para o título vazio.");
		
		//Então
		assertEquals("O novo título não pode ser vazio.", excecao.getMessage());
	}
	@Test
	@DisplayName("RF01c - Deve lançar IllegalArgumentException ao criar uma tarefa com ID nulo.")
	void req01c_deveLancarExcecao_quandoCriarTarefaComIdNulo() {
		//Dado
		String titulo = "Estudar TDD";
		String descricao = "Aprender R-G-R";

		//Quando		
		IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, 
				() -> new Tarefa(null, titulo, descricao),
				"Deveria lançar uma IllegalArgumentException para o título vazio.");
		
		//Então
		assertEquals("ID da tarefa não pode ser nulo!", excecao.getMessage());
	}
}