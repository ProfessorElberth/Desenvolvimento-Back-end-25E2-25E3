package br.edu.infnet.elberthapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.exceptions.BonusInvalidoException;

class VendedorTest {
	
	@ParameterizedTest(name = "Salário: {0}, Bônus: {1}, Ativo: {2} -> Esperado: {3}")
	@CsvSource({
		"1000.0, 500.0, true, 1500.0",
		"10.0, 5.0, false, 0.0"
	})
	void deveCalcularSalarioCorretamente_DadoMuitasEntradas(double salarioInicial, double bonus, boolean ativo, double salarioCalculado) {
		//Dado: configuração inicial do cenário
		Vendedor vendedor = new Vendedor("Elberth", 123, salarioInicial, ativo);
		
		//Quando: execução da ação a ser testada
		double novoSalario = vendedor.calcularSalario(bonus);
		
		//Então: verificação do resultado esperado
		assertEquals(salarioCalculado, novoSalario, 
				"O salário de um vendedor ativo com bônus deveria ser o salário + bônus.");
	}

	@Test
	void deveLancarExcecao_QuandoBonusZeradoOuNegativo() {
		//Dado: configuração inicial do cenário
		double salarioInicial = 1000;
		boolean ativo = true;
		double bonus = 0;
		String mensagemEsperada = "O valor do bônus não pode ser negativo ou igual a zero!";
		
		Vendedor vendedor = new Vendedor("Elberth", 123, salarioInicial, ativo);
		
		//Quando: execução da ação a ser testada		
		BonusInvalidoException excecaoLancada = assertThrows(BonusInvalidoException.class, () -> vendedor.calcularSalario(bonus), 
				"Deveria ter lançado uma BonusInvalidoException quando o bônus for zerado (ou negativo)"
			);
				
		//Então: verificação do resultado esperado
		assertEquals(mensagemEsperada, excecaoLancada.getMessage(), "Apresentação do assert");
	}

	@Test
	void deveCalcularSalarioComBonus_QuandoVendedorAtivo_EntaoRetornaSalarioComBonus() {
		//Dado: configuração inicial do cenário
		Vendedor vendedor = new Vendedor("Elberth", 123, 1000, true);
		double bonus = 500;
		double salarioCalculado = 1500;
		
		//Quando: execução da ação a ser testada
		double novoSalario = vendedor.calcularSalario(bonus);
		
		//Então: verificação do resultado esperado
		assertEquals(salarioCalculado, novoSalario, 
				"O salário de um vendedor ativo com bônus deveria ser o salário + bônus.");
	}
	
	@Test
	void retornarZeroParaSalarioVendedorInativo() {
		Vendedor vendedor = new Vendedor("Elberth", 123, 1000, false);
		
		double novoSalario = vendedor.calcularSalario(500);
		
		assertEquals(0, novoSalario);
	}
}