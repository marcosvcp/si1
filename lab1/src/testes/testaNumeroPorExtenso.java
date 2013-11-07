package testes;

import static org.junit.Assert.assertEquals;
import main.NumberManager;
import main.Numero;

import org.junit.Test;

public class testaNumeroPorExtenso {

	@Test
	private void testaUnidadesNumerosPorExtenso() {
		assertEquals(NumberManager.parse("0"), Numero.ZERO);
		assertEquals(NumberManager.parse("1"), Numero.UM);
		assertEquals(NumberManager.parse("2"), Numero.DOIS);
		assertEquals(NumberManager.parse("3"), Numero.TRES);
		assertEquals(NumberManager.parse("4"), Numero.QUATRO);
		assertEquals(NumberManager.parse("5"), Numero.CINCO);
		assertEquals(NumberManager.parse("6"), Numero.SEIS);
		assertEquals(NumberManager.parse("7"), Numero.SETE);
		assertEquals(NumberManager.parse("8"), Numero.OITO);
		assertEquals(NumberManager.parse("9"), Numero.NOVE);
	}

	@Test
	private void testaDezenasNumerosPorExtenso() {
		assertEquals(NumberManager.parse("10"), Numero.DEZ);
		assertEquals(NumberManager.parse("11"), Numero.ONZE);
		assertEquals(NumberManager.parse("12"), Numero.DOZE);
		assertEquals(NumberManager.parse("13"), Numero.TREZE);
		assertEquals(NumberManager.parse("14"), Numero.CATORZE);
		assertEquals(NumberManager.parse("15"), Numero.QUINZE);
		assertEquals(NumberManager.parse("16"), Numero.DEZESSEIS);
		assertEquals(NumberManager.parse("17"), Numero.DEZESSETE);
		assertEquals(NumberManager.parse("18"), Numero.DEZOITO);
		assertEquals(NumberManager.parse("19"), Numero.DEZENOVE);
		assertEquals(NumberManager.parse("20"), Numero.VINTE);
		assertEquals(NumberManager.parse("30"), Numero.TRINTA);
		assertEquals(NumberManager.parse("40"), Numero.QUARENTA);
		assertEquals(NumberManager.parse("50"), Numero.CINQUENTA);
		assertEquals(NumberManager.parse("60"), Numero.SESSENTA);
		assertEquals(NumberManager.parse("70"), Numero.SETENTA);
		assertEquals(NumberManager.parse("80"), Numero.OITENTA);
		assertEquals(NumberManager.parse("90"), Numero.NOVENTA);
	}

	@Test
	private void testaCentenasNumerosPorExtenso() {
		assertEquals("cento", Numero.CENTO);
		assertEquals(NumberManager.parse("100"), Numero.CEM);
		assertEquals(NumberManager.parse("200"), Numero.DUZENTOS);
		assertEquals(NumberManager.parse("300"), Numero.TREZENTOS);
		assertEquals(NumberManager.parse("400"), Numero.QUATROCENTOS);
		assertEquals(NumberManager.parse("500"), Numero.QUINHENTOS);
		assertEquals(NumberManager.parse("600"), Numero.SEISCENTOS);
		assertEquals(NumberManager.parse("700"), Numero.SETECENTOS);
		assertEquals(NumberManager.parse("800"), Numero.OITOCENTOS);
		assertEquals(NumberManager.parse("900"), Numero.NOVECENTOS);

	}

	@Test
	private void testaCombinacoesPorExtenso() {
		assertEquals(NumberManager.parse("101"),
				NumberManager.join(Numero.CENTO, Numero.ZERO, Numero.UM));

	}
}
