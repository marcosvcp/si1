package main;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.google.common.base.Strings;

/**
 * Essa classe � utilizada como um controlador de n�meros. Ela faz uso de um
 * parser para extenso de um n�mero dado como String.
 */
public class NumberManager {

	public static final String INITIAL_PROMPT = "Digite um n�mero natural entre 0 e 1000000000 (um bilh�o)";
	public static final String ERROR_PROMPT = "Apenas n�meros naturais podem ser considerado v�lidos";
	private final String ESPACO = " ";
	private HashMap<Integer, Number> numerationMap;

	public NumberManager() {
		numerationMap = new HashMap<Integer, Number>();
		buildNumerationMap();

	}

	/**
	 * Constr�i o mapa que guarda os nomes das casas dos n�meros
	 */
	private void buildNumerationMap() {
		getNumerationMap().put(1, Number.MIL);
		getNumerationMap().put(2, Number.MILHOES);
		getNumerationMap().put(3, Number.BILHOES);
	}

	/**
	 * Transforma um n�mero dado como String em forma de n�mero Natural e
	 * retorna o nome do n�mero por extenso.
	 * 
	 * @return O n�mero por extenso.
	 */
	public String parse(String number) {

		StringBuilder numberFullOutPut = new StringBuilder("");
		String[] numberArray = separaNumeros(number);
		String casaSelecionada;
		for (int index = 0; index < numberArray.length; index++) {
			// Numero corrente
			String numberNow = numberArray[index];

			String unidade = "";
			String centena = "";
			String dezena = "";

			// As tres ultimas casas do n�mero n�o tem nomenclatura
			// especial(mil, milh�o.. etc)
			casaSelecionada = index == numberArray.length - 1 ? ""
					: getNumerationMap().get((numberArray.length - 1) - index)
							.toString();

			switch (numberNow.length()) {
			case 3:
				if (isSpecialCase(numberNow, 3)) {
					unidade = "";
					dezena = searchValue(Number.values(),
							numberNow.substring(1)).toString()
							+ ESPACO;
				} else {
					unidade = searchValue(Number.values(),
							numberNow.substring(2)).toString()
							+ ESPACO;
					dezena = searchValue(Number.values(),
							(numberNow.toCharArray()[1] + "d")).toString()
							+ ESPACO;
				}
				centena = searchValue(Number.values(),
						numberNow.toCharArray()[0] + "c").toString()
						+ ESPACO;
				break;
			case 2:
				if (isSpecialCase(numberNow, 2)) {
					unidade = "";
					dezena = searchValue(Number.values(),
							numberNow.substring(0)).toString()
							+ ESPACO;
				} else {
					unidade = searchValue(Number.values(),
							numberNow.substring(1)).toString()
							+ ESPACO;
					dezena = searchValue(Number.values(),
							(numberNow.toCharArray()[0] + "d")).toString()
							+ ESPACO;
				}
				break;
			case 1:
				unidade = searchValue(Number.values(), numberNow.substring(0))
						.toString();
				break;
			default:
				break;
			}

			numberFullOutPut.append(join(centena, dezena, unidade) + ESPACO
					+ casaSelecionada + ESPACO);
		}

		return numberFullOutPut.toString();
	}

	/**
	 * Retorna se o n�mero tem tratamento especial caso ele seja da casa das
	 * dezenas entre 10 e 20, que s�o nomes diferenciados.
	 * 
	 * @return true caso o n�mero comece com 1.
	 */
	private boolean isSpecialCase(String numberNow, int length) {
		return (numberNow.toCharArray()[length - 2] == ('1'));

	}

	/**
	 * Procura o valor refere ao {@code numberToSearch} dentro dos valores
	 * apresentados do Enum.
	 */
	private Number searchValue(Number[] values, String numberToSearch) {
		for (Number number : values) {
			if (number.getValue().equals(numberToSearch)) {
				return number;
			}
		}
		return null;
	}

	/**
	 * O Algoritmo que eu usei foi separar as casas de tr�s em tr�s, de forma
	 * que fique mais simples utilizar a nomenclatura. Ele faz uso de REGEX, O
	 * intuito do algoritmo � apenas retornar um array contendo as Strings dos
	 * n�meros separadas.
	 * 
	 * @see { http
	 *      ://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.
	 *      html }
	 */
	private String[] separaNumeros(String number) {
		StringBuffer numberInputBuffer = new StringBuffer(number);
		String inverseNumber = numberInputBuffer.reverse().toString();
		String inverseNumberReplaced = inverseNumber.replaceAll("[0-9]{3}",
				"$0s");
		String reverseNumber = new StringBuffer(inverseNumberReplaced)
				.reverse().toString();
		String[] numberArray = reverseNumber.split("s");
		return numberArray;
	}

	/**
	 * Junta todos os n�meros dados os colocando entre conectivos.
	 */
	public String join(String... numbers) {
		StringBuilder joinNumbersString = new StringBuilder("");
		for (int index = 0; index < numbers.length; index++) {
			if (!Strings.isNullOrEmpty(joinNumbersString.toString())) {
				if (numbers.length >= 3 && index != 1) {
					joinNumbersString.append("e ");
				} else if (numbers.length < 3) {
					joinNumbersString.append("e ");
				}
			}
			joinNumbersString.append(numbers[index]);
		}
		return joinNumbersString.toString();
	}

	/**
	 * Le o n�mero e faz todo o tratamento para se transformar em extenso.
	 * 
	 * @throws OperationNotSupportedExcetion
	 *             Caso n�o seja um n�mero passado como par�metro ou ele n�o
	 *             esteja no intervalo permitido.
	 */
	public String readInput(String numberInput)
			throws OperationNotSupportedException {
		if (!numberInput.matches("[0-9]{1,9}")) {
			throw new OperationNotSupportedException(
					"A entrada deve ser n�o vazia e apenas n�meros naturais entre 0 e 1000000000 (um bilh�o)");
		}
		return parse(numberInput);
	}

	public HashMap<Integer, Number> getNumerationMap() {
		return numerationMap;
	}

	public void setNumerationMap(HashMap<Integer, Number> numberMap) {
		numerationMap = numberMap;
	}
}