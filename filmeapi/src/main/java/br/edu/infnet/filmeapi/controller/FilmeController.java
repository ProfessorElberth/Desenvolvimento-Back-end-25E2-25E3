package br.edu.infnet.filmeapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.filmeapi.model.domain.Filme;
import br.edu.infnet.filmeapi.model.service.FilmeService;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

	private final FilmeService filmeService;
	
	public FilmeController(FilmeService filmeService) {
		this.filmeService = filmeService;
	}
	
	@GetMapping
	public ResponseEntity<List<Filme>> obterLista() {

		List<Filme> lista = filmeService.obterLista();
		
		return new ResponseEntity<List<Filme>>(lista, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Filme> incluir(@RequestBody Filme filme) {
		
		Filme novoFilme = filmeService.incluir(filme);
		
		return new ResponseEntity<Filme>(novoFilme, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		
		filmeService.excluir(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Filme> alterar(@PathVariable Integer id, @RequestBody Filme filme) {
		
		Filme filmeAlterado = filmeService.alterar(id, filme);
		
		return new ResponseEntity<Filme>(filmeAlterado, HttpStatus.OK);
		
	}
}