package br.edu.infnet.elberthapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.edu.infnet.elberthapi.model.domain.CalculadoraCientifica;

class CalculadoraCientificaTest {

	private CalculadoraCientifica calculadora;
	
	@BeforeEach
	void configurarCalculadora() {
		calculadora = new CalculadoraCientifica();
	}
	
	@Test
	void deveSomar_QuandoNumeros_EntaoRetornarSomaCorreta() {
		//Dado - Given - Setup
		double numero1 = 10;
		double numero2 = 4;
		double resultadoEsperado = 14;
		
		//Quando - When - execution
		double resultadoAtual = calculadora.add(numero1, numero2);
		
		//Então - Then - assertion
		assertEquals(resultadoEsperado, resultadoAtual, "A soma de dois números não está correta");
	}

	@Test
	void deveSubtrair_QuandoNumeros_EntaoRetornarSubtracaoCorreta() {
		//Dado - Given - Setup
		double numero1 = 10;
		double numero2 = 4;
		double resultadoEsperado = 6;
		
		//Quando - When - execution
		double resultadoAtual = calculadora.subtract(numero1, numero2);
		
		//Então - Then - assertion
		assertEquals(resultadoEsperado, resultadoAtual, "A subtração de dois números não está correta");
	}

	@Test
	void deveCalcularRaizQuadrada_QuandoNumeros_EntaoRetornarRaizQuadradaCorreta() {
		//Dado - Given - Setup
		double numero = 4;
		double resultadoEsperado = 2;
		
		//Quando - When - execution
		double resultadoAtual = calculadora.squareRoot(numero);
		
		//Então - Then - assertion
		assertEquals(resultadoEsperado, resultadoAtual, "A raiz quadrada de um número deveria ser calculada corretamente!");
	}

	@Test
	void deveLancarExcecao_QuandoNumeroNegativo_EntaoRetornarIllegalArgumentException() {
		//Dado - Given - Setup
		double numero = -4;
		String mensagemEsperada = "Negative number";
		
		//Quando - When - execution
		
		IllegalArgumentException excecaoLancada = assertThrows(IllegalArgumentException.class, () -> calculadora.squareRoot(numero),
				"Poderia ter lançada a IllegalArgumentException para o cálculo de raiz quadrada com número negativo!");
		
		
		//Então - Then - assertion
		assertEquals(mensagemEsperada, excecaoLancada.getMessage(), "A mensagem da exception está incorreta!");
	}
	
	@Test
	void deveCalcularLog_QuandoNumeroPositivo_EntaoRetornarValorCorreto() {
		//Dado - Given - Setup
		double numero = 8;
		double resultadoEsperado = 2.079;
		
		//Quando - When - execution
		double resultadoAtual = calculadora.log(numero);
		
		//Então - Then - assertion
		assertEquals(resultadoEsperado, resultadoAtual, 0.001, "Logaritmo não está correto!");
	}

	// -- Testes de funções trigonométricas --
	
	@ParameterizedTest(name = "O seno de {0} deve ser {1}.")
	@CsvSource({
		"0.0,0.0",
		"30.0,0.5",
		"90.0,1.0",
		"180.0,0.0"
	})
	@DisplayName("Deve calcular o valor correto do seno para alguns ângulos")
	void deveCalcularSeno_QuandoAngulos_EntaoRetornarValorCorreto(double grau, double seno) {
		//programador pragmático (livro)
		
		//Quando
		double novoSeno = calculadora.sin(grau);
		
		//Então
		assertEquals(seno, novoSeno, 0.01,
				String.format("O seno de %.2f deveria ser %.2f, mas foi %.2f", grau, seno, novoSeno)
			);
	}
	
	//cosseno
}
























