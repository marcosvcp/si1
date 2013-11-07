package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testaNumeroPorExtenso {

	private NumberExtensive numberExtensive;

	@Before
	public void setUp() {

	}

	@Test
	private void testaUnidadesPorExtenso() {
		assertEquals(numberExtensive.parseNumberToExtensive("0"),
				UNIDADE.ZERO);
		assertEquals(numberExtensive.parseNumberToExtensive("1"), UNIDADE.UM);
		assertEquals(numberExtensive.parseNumberToExtensive("2"), UNIDADE.DOIS);
		assertEquals(numberExtensive.parseNumberToExtensive("3"), UNIDADE.TRES);
		assertEquals(numberExtensive.parseNumberToExtensive("4"), UNIDADE.QUATRO);
		assertEquals(numberExtensive.parseNumberToExtensive("5"), UNIDADE.CINCO);
		assertEquals(numberExtensive.parseNumberToExtensive("6"), UNIDADE.SEIS);
		assertEquals(numberExtensive.parseNumberToExtensive("7"), UNIDADE.SETE);
		assertEquals(numberExtensive.parseNumberToExtensive("8"), UNIDADE.OITO);
		assertEquals(numberExtensive.parseNumberToExtensive("9"), UNIDADE.NOVE);
	}
	
	@Test
	private void testaDezenasPorExtenso() {
		assertEquals(numberExtensive.parseNumberToExtensive("10"),
				DEZENA.DEZ);
		assertEquals(numberExtensive.parseNumberToExtensive("11"),
				DEZENA.ONZE);
		assertEquals(numberExtensive.parseNumberToExtensive("12"),
				DEZENA.DOZE);
		assertEquals(numberExtensive.parseNumberToExtensive("13"),
				DEZENA.TREZE);
		assertEquals(numberExtensive.parseNumberToExtensive("14"),
				DEZENA.CATORZE);
		assertEquals(numberExtensive.parseNumberToExtensive("15"),
				DEZENA.QUINZE);
		assertEquals(numberExtensive.parseNumberToExtensive("16"),
				DEZENA.DEZESSEIS);
		assertEquals(numberExtensive.parseNumberToExtensive("17"),
				DEZENA.DEZESSETE);
		assertEquals(numberExtensive.parseNumberToExtensive("18"),
				DEZENA.DEZOITO);
		assertEquals(numberExtensive.parseNumberToExtensive("19"),
				DEZENA.DEZENOVE);
		assertEquals(numberExtensive.parseNumberToExtensive("20"),
				DEZENA.VINTE);
		assertEquals(numberExtensive.parseNumberToExtensive("30"), DEZENA.TRINTA);
		assertEquals(numberExtensive.parseNumberToExtensive("40"), DEZENA.QUARENTA);
		assertEquals(numberExtensive.parseNumberToExtensive("50"), DEZENA.CINQUENTA);
		assertEquals(numberExtensive.parseNumberToExtensive("60"), DEZENA.SESSENTA);
		assertEquals(numberExtensive.parseNumberToExtensive("70"), DEZENA.SETENTA);
		assertEquals(numberExtensive.parseNumberToExtensive("80"), DEZENA.OITENTA);
		assertEquals(numberExtensive.parseNumberToExtensive("90"), DEZENA.NOVENTA);
	}

}
