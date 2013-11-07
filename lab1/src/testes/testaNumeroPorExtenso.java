package testes;

import static org.junit.Assert.assertEquals;
import main.Number;
import main.NumberManager;

import org.junit.Test;

public class testaNumeroPorExtenso {

	@Test
	public void testaUnidadesNumerosPorExtenso() {
		assertEquals(NumberManager.parse("0"), Number.ZERO);
		assertEquals(NumberManager.parse("1"), Number.UM);
		assertEquals(NumberManager.parse("2"), Number.DOIS);
		assertEquals(NumberManager.parse("3"), Number.TRES);
		assertEquals(NumberManager.parse("4"), Number.QUATRO);
		assertEquals(NumberManager.parse("5"), Number.CINCO);
		assertEquals(NumberManager.parse("6"), Number.SEIS);
		assertEquals(NumberManager.parse("7"), Number.SETE);
		assertEquals(NumberManager.parse("8"), Number.OITO);
		assertEquals(NumberManager.parse("9"), Number.NOVE);
	}

	@Test
	public void testaDezenasNumerosPorExtenso() {
		assertEquals(NumberManager.parse("10"), Number.DEZ);
		assertEquals(NumberManager.parse("11"), Number.ONZE);
		assertEquals(NumberManager.parse("12"), Number.DOZE);
		assertEquals(NumberManager.parse("13"), Number.TREZE);
		assertEquals(NumberManager.parse("14"), Number.CATORZE);
		assertEquals(NumberManager.parse("15"), Number.QUINZE);
		assertEquals(NumberManager.parse("16"), Number.DEZESSEIS);
		assertEquals(NumberManager.parse("17"), Number.DEZESSETE);
		assertEquals(NumberManager.parse("18"), Number.DEZOITO);
		assertEquals(NumberManager.parse("19"), Number.DEZENOVE);
		assertEquals(NumberManager.parse("20"), Number.VINTE);
		assertEquals(NumberManager.parse("30"), Number.TRINTA);
		assertEquals(NumberManager.parse("40"), Number.QUARENTA);
		assertEquals(NumberManager.parse("50"), Number.CINQUENTA);
		assertEquals(NumberManager.parse("60"), Number.SESSENTA);
		assertEquals(NumberManager.parse("70"), Number.SETENTA);
		assertEquals(NumberManager.parse("80"), Number.OITENTA);
		assertEquals(NumberManager.parse("90"), Number.NOVENTA);
	}

	@Test
	public void testaCentenasNumerosPorExtenso() {
		final String CENTO = "cento";
		assertEquals(CENTO, Number.CENTO);
		assertEquals(NumberManager.parse("100"), Number.CEM);
		assertEquals(NumberManager.parse("200"), Number.DUZENTOS);
		assertEquals(NumberManager.parse("300"), Number.TREZENTOS);
		assertEquals(NumberManager.parse("400"), Number.QUATROCENTOS);
		assertEquals(NumberManager.parse("500"), Number.QUINHENTOS);
		assertEquals(NumberManager.parse("600"), Number.SEISCENTOS);
		assertEquals(NumberManager.parse("700"), Number.SETECENTOS);
		assertEquals(NumberManager.parse("800"), Number.OITOCENTOS);
		assertEquals(NumberManager.parse("900"), Number.NOVECENTOS);
	}

	@Test
	public void testaExcecoes() {
		final String STRING_VAZIA = "";
		final String INVALID_INPUT = "Qualquercoisa";
		assertEquals(NumberManager.INITIAL_PROMPT,
				NumberManager.readInput(STRING_VAZIA));
		assertEquals(NumberManager.ERROR_PROMPT,
				NumberManager.readInput(INVALID_INPUT));
	}

	@Test
	public void testaCombinacoesPorExtenso() {
		assertEquals(NumberManager.parse("101"),
				NumberManager.join(Number.CENTO, Number.ZERO, Number.UM));

	}
}
