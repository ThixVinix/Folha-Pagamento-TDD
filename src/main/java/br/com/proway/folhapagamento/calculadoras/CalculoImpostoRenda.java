package br.com.proway.folhapagamento.calculadoras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculoImpostoRenda {

	private static final Logger LOGGER = LogManager.getLogger(CalculoImpostoRenda.class.getName());

	private static final String MSG_1 = "Alíquota isenta identificada. Salário igual ou abaixo de \"R$ {}\".";
	private static final String MSG_2 = "Alíquota \"{}\" identificada. Salário entre \"R$ {}\" e \"R$ {}\".";
	private static final String MSG_3 = "Alíquota \"{}\" identificada. Salário igual ou acima de \"R$ {}\".";

	public double calcular(double salario) {
		LOGGER.info("Calculando imposto de renda do salário: \"R$ {}\"...", salario);

		final double aliquota1 = 0.075d;
		final double aliquota2 = 0.15d;
		final double aliquota3 = 0.225d;
		final double aliquota4 = 0.275d;

		final double faixa1 = 1903.98d;
		final double[] faixa2 = { 1903.99d, 2826.65d };
		final double[] faixa3 = { 2826.66d, 3751.05d };
		final double[] faixa4 = { 3751.06d, 4664.68d };
		final double faixa5 = 4664.69;

		double imposto;

		if (salario <= faixa1) {
			LOGGER.debug(MSG_1, faixa1);

			imposto = 0.0d;

		} else if (salario >= faixa2[0] && salario <= faixa2[1]) {
			LOGGER.debug(MSG_2, aliquota1, faixa2[0], faixa2[1]);

			double valorDescontado1 = (salario - faixa1) * aliquota1;
			imposto = valorDescontado1;

		} else if (salario >= faixa3[0] && salario <= faixa3[1]) {
			LOGGER.debug(MSG_2, aliquota2, faixa3[0], faixa3[1]);

			double valorDescontado1 = (faixa2[1] - faixa2[0]) * aliquota1;
			double valorDescontado2 = (salario - faixa3[0]) * aliquota2;
			imposto = valorDescontado1 + valorDescontado2;

		} else if (salario >= faixa4[0] && salario <= faixa4[1]) {
			LOGGER.debug(MSG_2, aliquota3, faixa4[0], faixa4[1]);

			double valorDescontado1 = (faixa2[1] - faixa2[0]) * aliquota1;
			double valorDescontado2 = (faixa3[1] - faixa3[0]) * aliquota2;
			double valorDescontado3 = (salario - faixa4[0]) * aliquota3;
			imposto = valorDescontado1 + valorDescontado2 + valorDescontado3;

		} else {
			LOGGER.debug(MSG_3, aliquota4, faixa5);

			double valorDescontado1 = (faixa2[1] - faixa2[0]) * aliquota1;
			double valorDescontado2 = (faixa3[1] - faixa3[0]) * aliquota2;
			double valorDescontado3 = (faixa4[1] - faixa4[0]) * aliquota3;
			double valorDescontado4 = (salario - faixa5) * aliquota4;
			imposto = valorDescontado1 + valorDescontado2 + valorDescontado3 + valorDescontado4;
		}

		LOGGER.info("Resultado do imposto de renda: \"{}\".", imposto);
		return imposto;

	}

}
