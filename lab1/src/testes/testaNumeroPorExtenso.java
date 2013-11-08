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
		assertEquals(nm.parse("0"), Number.ZERO.toString());
		assertEquals(nm.parse("1"), Number.UM.toString());
		assertEquals(nm.parse("2"), Number.DOIS.toString());
		assertEquals(nm.parse("3"), Number.TRES.toString());
		assertEquals(nm.parse("4"), Number.QUATRO.toString());
		assertEquals(nm.parse("5"), Number.CINCO.toString());
		assertEquals(nm.parse("6"), Number.SEIS.toString());
		assertEquals(nm.parse("7"), Number.SETE.toString());
		assertEquals(nm.parse("8"), Number.OITO.toString());
		assertEquals(nm.parse("9"), Number.NOVE.toString());
	}

	@Test
	public void testaDezenasNumerosPorExtenso() {
		assertEquals(nm.parse("10"), Number.DEZ);
		assertEquals(nm.parse("11"), Number.ONZE);
		assertEquals(nm.parse("12"), Number.DOZE);
		assertEquals(nm.parse("13"), Number.TREZE);
		assertEquals(nm.parse("14"), Number.CATORZE);
		assertEquals(nm.parse("15"), Number.QUINZE);
		assertEquals(nm.parse("16"), Number.DEZESSEIS);
		assertEquals(nm.parse("17"), Number.DEZESSETE);
		assertEquals(nm.parse("18"), Number.DEZOITO);
		assertEquals(nm.parse("19"), Number.DEZENOVE);
		assertEquals(nm.parse("20"), Number.VINTE);
		assertEquals(nm.parse("30"), Number.TRINTA);
		assertEquals(nm.parse("40"), Number.QUARENTA);
		assertEquals(nm.parse("50"), Number.CINQUENTA);
		assertEquals(nm.parse("60"), Number.SESSENTA);
		assertEquals(nm.parse("70"), Number.SETENTA);
		assertEquals(nm.parse("80"), Number.OITENTA);
		assertEquals(nm.parse("90"), Number.NOVENTA);
	}

	@Test
	public void testaCentenasNumerosPorExtenso() {
		final String CENTO = "cento";
		assertEquals(CENTO, Number.CENTO.toString());
		assertEquals(nm.parse("100"), Number.CEM.toString());
		assertEquals(nm.parse("200"), Number.DUZENTOS.toString());
		assertEquals(nm.parse("300"), Number.TREZENTOS.toString());
		assertEquals(nm.parse("400"), Number.QUATROCENTOS.toString());
		assertEquals(nm.parse("500"), Number.QUINHENTOS.toString());
		assertEquals(nm.parse("600"), Number.SEISCENTOS.toString());
		assertEquals(nm.parse("700"), Number.SETECENTOS.toString());
		assertEquals(nm.parse("800"), Number.OITOCENTOS.toString());
		assertEquals(nm.parse("900"), Number.NOVECENTOS.toString());
	}

	@Test
	public void testaExcecoes() {
		final String STRING_VAZIA = "";
		final String INVALID_INPUT = "Qualquercoisa";
		try {
			nm.readInput(STRING_VAZIA);
			fail("Era pra ter lançado excessão");
		} catch (OperationNotSupportedException exception) {
			// ok
		}
		try {
			nm.readInput(INVALID_INPUT);
			fail("Era pra ter lançado excessão");
		} catch (OperationNotSupportedException exception) {
			// ok
		}
	}

	@Test
	public void testaCombinacoesPorExtenso() {
		assertEquals(nm.parse("101"), "cento e um");
		assertEquals(nm.parse("123412"),
				"centro e vinte e tres mil quatrocentos e doze");
		assertEquals(nm.parse("6434"),
				"seis mil quatrocentos e trinca e quatro");

	}
}
