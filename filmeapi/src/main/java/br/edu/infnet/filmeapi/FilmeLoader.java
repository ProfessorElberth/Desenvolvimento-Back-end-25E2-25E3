package br.edu.infnet.filmeapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.filmeapi.model.domain.Ator;
import br.edu.infnet.filmeapi.model.domain.Filme;
import br.edu.infnet.filmeapi.model.domain.Genero;
import br.edu.infnet.filmeapi.model.service.AtorService;
import br.edu.infnet.filmeapi.model.service.FilmeService;

@Component
public class FilmeLoader implements ApplicationRunner {

	private final FilmeService filmeService;
	private final AtorService atorService;
	
	public FilmeLoader(FilmeService filmeService, AtorService atorService) {
		this.filmeService = filmeService;
		this.atorService = atorService;
	}
	
	@Override
	public void run(ApplicationArguments args) {

		String arquivo = "arquivos/filmes.txt";
		
		FileReader fileReader;
		try {
			fileReader = new FileReader(arquivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = bufferedReader.readLine();
			
			String[] campos = null;
			
			while(linha != null) {	
			
				campos = linha.split(";");
				
				Integer idAtor = Integer.valueOf(campos[5]);
				
				Ator ator = atorService.obterPorId(idAtor);
				
				Filme filme = new Filme();
				filme.setTitulo(campos[0]);
				filme.setAnoLancamento(Integer.valueOf(campos[1]));
				filme.setAvaliacaoMedia(Double.valueOf(campos[2]));
				filme.setEmCartaz(Boolean.valueOf(campos[3]));
				filme.setGenero(Genero.valueOf(campos[4]));
				filme.setAtorPrincipal(ator);
				
				filmeService.incluir(filme);
				
				linha = bufferedReader.readLine();
			}

			for(Filme filme: filmeService.obterLista()) {
				System.out.println("# " + filme);
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Problemas na abertura/fechamento do arquivo: " + e.getMessage());
		}
		
	}
}