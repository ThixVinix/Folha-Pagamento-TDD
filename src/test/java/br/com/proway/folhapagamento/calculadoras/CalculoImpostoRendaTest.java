package br.com.proway.folhapagamento.calculadoras;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Thiago Vinicius de Almeida Souza
 *
 */
@DisplayName("Testes da Calculadora do Imposto de Renda")
class CalculoImpostoRendaTest {

	private static final String INITIALIZE_MESSAGE = "Inicializando teste...";

	private static final Logger LOGGER = LogManager.getLogger(CalculoImpostoRendaTest.class.getName());

	private static final String DIRETORIO_ALVO = CalculoImpostoRendaTest.class.getCanonicalName();

	private static CalculoImpostoRenda calculoImpostoRenda;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LOGGER.info("Iniciando os testes da classe: \"" + DIRETORIO_ALVO + "\"...\n");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		LOGGER.info("Todos os testes da classe: \"" + DIRETORIO_ALVO + "\" foram finalizados.");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		calculoImpostoRenda = new CalculoImpostoRenda();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		LOGGER.info("Teste finalizado.\n");
	}

	/**
	 * Test method for
	 * {@link br.com.proway.folhapagamento.calculadoras.CalculoImpostoRenda#calcular(double)}.
	 */
	@DisplayName("Calculo do imposto de renda com salario entre R$ 0,00 ate R$ 1.903,98")
	@ValueSource(doubles = { -1d, 0.0d, 1000.50d, 1903.98d })
	@ParameterizedTest(name = "Teste {index}: Salário = R$ {arguments}")
	void testeImpostoRendaDeveSer0(double salario) {
		LOGGER.info(INITIALIZE_MESSAGE);
		double impostoRenda = calculoImpostoRenda.calcular(salario);
		assertEquals(0.0d, impostoRenda, 0.0001d);
	}

	/**
	 * Test method for
	 * {@link br.com.proway.folhapagamento.calculadoras.CalculoImpostoRenda#calcular(double)}.
	 */
	@DisplayName("Calculos do imposto de renda com salários entre R$ 1.903,99 ate R$ 2.826,65")
	@Test
	void testeImpostoRendaFaixa2() {
		LOGGER.info(INITIALIZE_MESSAGE);

		final double SALARIO_1 = 1903.99d;
		final double SALARIO_2 = 2500.00d;
		final double SALARIO_3 = 2826.65d;

		assertAll(() -> {
			double impostoRenda1 = calculoImpostoRenda.calcular(SALARIO_1);
			assertEquals(0.0007d, impostoRenda1, 0.0001d);
			double impostoRenda2 = calculoImpostoRenda.calcular(SALARIO_2);
			assertEquals(44.7015d, impostoRenda2, 0.0001d);
			double impostoRenda3 = calculoImpostoRenda.calcular(SALARIO_3);
			assertEquals(69.2002d, impostoRenda3, 0.0001d);
		});

	}

	/**
	 * Test method for
	 * {@link br.com.proway.folhapagamento.calculadoras.CalculoImpostoRenda#calcular(double)}.
	 */
	@DisplayName("Calculos do imposto de renda com salarios entre R$ 2.826,66 ate R$ 3.751,05")
	@Test
	void testeImpostoRendaFaixa3() {
		LOGGER.info(INITIALIZE_MESSAGE);

		final double SALARIO_1 = 2826.66d;
		final double SALARIO_2 = 3500.00d;
		final double SALARIO_3 = 3751.05d;

		assertAll(() -> {
			double impostoRenda1 = calculoImpostoRenda.calcular(SALARIO_1);
			assertEquals(69.1995d, impostoRenda1, 0.0001d);
			double impostoRenda2 = calculoImpostoRenda.calcular(SALARIO_2);
			assertEquals(170.2005d, impostoRenda2, 0.0001d);
			double impostoRenda3 = calculoImpostoRenda.calcular(SALARIO_3);
			assertEquals(207.8580d, impostoRenda3, 0.0001d);
		});
	}

	/**
	 * Test method for
	 * {@link br.com.proway.folhapagamento.calculadoras.CalculoImpostoRenda#calcular(double)}.
	 */
	@DisplayName("Calculos do imposto de renda com salarios entre R$ 3.751,06 ate R$ 4.664,68")
	@Test
	void testeImpostoRendaFaixa4() {
		LOGGER.info(INITIALIZE_MESSAGE);

		final double SALARIO_1 = 3751.06d;
		final double SALARIO_2 = 4000.00d;
		final double SALARIO_3 = 4664.68d;

		assertAll(() -> {
			double impostoRenda1 = calculoImpostoRenda.calcular(SALARIO_1);
			assertEquals(207.8580d, impostoRenda1, 0.0001d);
			double impostoRenda2 = calculoImpostoRenda.calcular(SALARIO_2);
			assertEquals(263.8695d, impostoRenda2, 0.0001d);
			double impostoRenda3 = calculoImpostoRenda.calcular(SALARIO_3);
			assertEquals(413.4225d, impostoRenda3, 0.0001d);
		});
	}

	/**
	 * Test method for
	 * {@link br.com.proway.folhapagamento.calculadoras.CalculoImpostoRenda#calcular(double)}.
	 */
	@DisplayName("Calculo do imposto de renda com salario acima de R$ 4.664,68")
	@Test
	void testeImpostoRenda5() {
		LOGGER.info(INITIALIZE_MESSAGE);

		final double SALARIO_1 = 4664.69d;
		final double SALARIO_2 = 5000.00d;
		final double SALARIO_3 = 10000.00d;

		assertAll(() -> {
			double impostoRenda1 = calculoImpostoRenda.calcular(SALARIO_1);
			assertEquals(413.4225d, impostoRenda1, 0.0001d);
			double impostoRenda2 = calculoImpostoRenda.calcular(SALARIO_2);
			assertEquals(505.6327d, impostoRenda2, 0.0001d);
			double impostoRenda3 = calculoImpostoRenda.calcular(SALARIO_3);
			assertEquals(1880.6327d, impostoRenda3, 0.0001d);
		});
	}

}
