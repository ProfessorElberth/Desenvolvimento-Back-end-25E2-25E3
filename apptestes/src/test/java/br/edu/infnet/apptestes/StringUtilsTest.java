package br.edu.infnet.apptestes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringUtilsTest {
	
	private StringUtils str;
	
	@BeforeEach
	void setup() {
		str = new StringUtils();
	}

	// -- Reverter textos --
	
	@Test
	@DisplayName("Teste para reverter uma String")
	void deveReverter_QuandoStringNormal_EntaoRetornarInvertida() {
		// Dado
		String textoOriginal = "Elberth";
		String textoEsperado = "htreblE";
		
		// Quando
		String textoAtual = str.reverter(textoOriginal);
		
		// Então
		assertEquals(textoEsperado, textoAtual, 
				String.format("O %s deveria estar invertido como %s.", textoOriginal, textoEsperado)
			);
	}

	@Test
	@DisplayName("Deve lançar uma IllegalArgumentException quando o texto for null")
	void deveReverter_QuandoTextoNulo_EntaoLancarIllegalArgumentException() {
		// Dado
		String textoOriginal = null;
		String mensagemEsperada = "O texto não pode ser nulo!";
		
		// Quando
		IllegalArgumentException excecao = assertThrows(
				IllegalArgumentException.class, 
				() -> str.reverter(textoOriginal),
				"Deveria lançar a exceção IllegalArgumentException quando o texto for nulo."
			);
				
		// Então
		assertEquals(mensagemEsperada, excecao.getMessage(), "A mensagem de exceção está incorreta!");
	}
	
	// -- Palíndromo
	
	@ParameterizedTest(name = "O texto {0} deve ser palíndromo: {1}")
	@CsvSource({
		"omo,true",
		"ovo,true",
		"elberth,false"
	})
	@DisplayName("Deve verificar se um texto é um palíndromo.")
	void deveVerificarPalindromo_ComVariasStrings(String texto, boolean resultadoEsperado) {
		// Quando
		boolean resultadoAtual = str.ehPalindromo(texto);
		
		// Então
		assertEquals(resultadoEsperado, resultadoAtual, "Deveria ser um palíndromo!");
	}
}