package br.edu.infnet.filmeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Filme> obterLista() {

		return filmeService.obterLista();
	}
}