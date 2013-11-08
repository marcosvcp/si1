package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import main.Number;
import main.NumberManager;

import org.junit.Before;
import org.junit.Test;

public class testaNumeroPorExtenso {
	private NumberManager nm;

	@Before
	public void setUp() {
		nm = new NumberManager();
	}

	@Test
	public void testaUnidadesNumerosPorExtenso() {
		assertEquals(Number.ZERO.getNumberName(), nm.parse("0"));
		assertEquals(Number.UM.getNumberName(), nm.parse("1"));
		assertEquals(Number.DOIS.getNumberName(), nm.parse("2"));
		assertEquals(Number.TRES.getNumberName(), nm.parse("3"));
		assertEquals(Number.QUATRO.getNumberName(), nm.parse("4"));
		assertEquals(Number.CINCO.getNumberName(), nm.parse("5"));
		assertEquals(Number.SEIS.getNumberName(), nm.parse("6"));
		assertEquals(Number.SETE.getNumberName(), nm.parse("7"));
		assertEquals(Number.OITO.getNumberName(), nm.parse("8"));
		assertEquals(Number.NOVE.getNumberName(), nm.parse("9"));
	}

	@Test
	public void testaDezenasNumerosPorExtenso() {
		assertEquals(Number.DEZ.getNumberName(), nm.parse("10"));
		assertEquals(Number.ONZE.getNumberName(), nm.parse("11"));
		assertEquals(Number.DOZE.getNumberName(), nm.parse("12"));
		assertEquals(Number.TREZE.getNumberName(), nm.parse("13"));
		assertEquals(Number.CATORZE.getNumberName(), nm.parse("14"));
		assertEquals(Number.QUINZE.getNumberName(), nm.parse("15"));
		assertEquals(Number.DEZESSEIS.getNumberName(), nm.parse("16"));
		assertEquals(Number.DEZESSETE.getNumberName(), nm.parse("17"));
		assertEquals(Number.DEZOITO.getNumberName(), nm.parse("18"));
		assertEquals(Number.DEZENOVE.getNumberName(), nm.parse("19"));
		assertEquals(Number.VINTE.getNumberName(), nm.parse("20"));
		assertEquals(Number.TRINTA.getNumberName(), nm.parse("30"));
		assertEquals(Number.QUARENTA.getNumberName(), nm.parse("40"));
		assertEquals(Number.CINQUENTA.getNumberName(), nm.parse("50"));
		assertEquals(Number.SESSENTA.getNumberName(), nm.parse("60"));
		assertEquals(Number.SETENTA.getNumberName(), nm.parse("70"));
		assertEquals(Number.OITENTA.getNumberName(), nm.parse("80"));
		assertEquals(Number.NOVENTA.getNumberName(), nm.parse("90"));
	}

	@Test
	public void testaCentenasNumerosPorExtenso() {
		final String CENTO = "cento";
		assertEquals(Number.CENTO.getNumberName(), CENTO);
		assertEquals(Number.CEM.getNumberName(), nm.parse("100"));
		assertEquals(Number.DUZENTOS.getNumberName(), nm.parse("200"));
		assertEquals(Number.TREZENTOS.getNumberName(), nm.parse("300"));
		assertEquals(Number.QUATROCENTOS.getNumberName(), nm.parse("400"));
		assertEquals(Number.QUINHENTOS.getNumberName(), nm.parse("500"));
		assertEquals(Number.SEISCENTOS.getNumberName(), nm.parse("600"));
		assertEquals(Number.SETECENTOS.getNumberName(), nm.parse("700"));
		assertEquals(Number.OITOCENTOS.getNumberName(), nm.parse("800"));
		assertEquals(Number.NOVECENTOS.getNumberName(), nm.parse("900"));
	}

	@Test
	public void testaExcecoes() {
		final String STRING_VAZIA = "";
		final String INVALID_INPUT = "Qualquercoisa";
		final String INVALID_INPUT_LETERS = "Abasd123";
		try {
			nm.readInput(STRING_VAZIA);
			fail("Era pra ter lançado excessão");
		} catch (IllegalArgumentException exception) {
			// ok
		} catch (OperationNotSupportedException e) {
			// ok
		}
		try {
			nm.readInput(INVALID_INPUT);
			fail("Era pra ter lançado excessão");
		} catch (IllegalArgumentException exception) {
			// ok
		} catch (OperationNotSupportedException e) {
			// ok
		}

		try {
			nm.readInput(INVALID_INPUT_LETERS);
			fail("Era pra ter lançado excessão");
		} catch (IllegalArgumentException exception) {
			// ok
		} catch (OperationNotSupportedException e) {
			// ok
		}
	}

	@Test
	public void testaCombinacoesPorExtenso() {
		assertEquals("cento e um", nm.parse("101"));
		assertEquals("cento e vinte e três mil quatrocentos e doze",
				nm.parse("123412"));
		assertEquals("seis mil quatrocentos e trinta e quatro",
				nm.parse("6434"));
		assertEquals("um bilhão", nm.parse("1000000000"));
		assertEquals("um milhão", nm.parse("1000000"));
		assertEquals("doze milhões cento e vinte e quatro mil seiscentos e oitenta", nm.parse("12124680"));
		assertEquals("um mil e catorze", nm.parse("1014"));
	}
}
