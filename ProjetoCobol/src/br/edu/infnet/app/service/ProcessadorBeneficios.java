package br.edu.infnet.app.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infnet.app.model.Beneficio;
import br.edu.infnet.app.model.ControleRevisao;

public class ProcessadorBeneficios {

	public void execute(String entrada, String saida) {
		
		List<ControleRevisao> controles = obterControleRevisao(entrada + "ControleRevisao.csv");
		Map<String, Beneficio> mapaBeneficio = obterBeneficios(entrada + "Beneficio.csv");

		//dependentes
		//movimentacoes
		
		try (BufferedWriter escrita = new BufferedWriter(new FileWriter(saida))) {

			for(ControleRevisao controle : controles) {
				
				String nb = controle.getNuNB();

				//BENEFÍCIO
				Beneficio benef = mapaBeneficio.get(nb);
				
				if (benef == null) {
					System.out.println("Deu ruin com NULL");
					continue;
				}
				
				if(!(benef.getCsEspecie() == 21 || benef.getCsEspecie() == 93)) {
					System.out.println("Deu ruin com ESPÉCIE");
					continue;
				}
				
				if(benef.getCdSituacaoBenef() == 0) {
					System.out.println("Deu ruin com SITUAÇÃO");
					continue;
				}

				//DEPENDENTE
				
				//MOVIMENTAÇÃO
				
				escrita.write(controle.getNuNB());
				escrita.newLine();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Beneficio> obterBeneficios(String path) {

		Map<String, Beneficio> mapa = new HashMap<String, Beneficio>();
		
		try (BufferedReader leitura = new BufferedReader(new FileReader(path))) {
			
			String linha = leitura.readLine();

			while((linha = leitura.readLine()) != null) {

				String[] campos = linha.split(",");
				
				Beneficio beneficio = new Beneficio(campos[0], Integer.valueOf(campos[1]), Integer.valueOf(campos[2]), campos[3], campos[4]);
				
				mapa.put(beneficio.getNuNB(), beneficio);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapa;
	}

	private List<ControleRevisao> obterControleRevisao(String path){
		
		List<ControleRevisao> lista = new ArrayList<ControleRevisao>();

		try (BufferedReader leitura = new BufferedReader(new FileReader(path))) {
			
			String linha = leitura.readLine();

			while((linha = leitura.readLine()) != null) {
				
				lista.add(new ControleRevisao(linha.trim()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lista;
	}
}