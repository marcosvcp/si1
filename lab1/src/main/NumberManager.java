package main;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.google.common.base.Strings;

/**
 * Essa classe � utilizada como um controlador de n�meros. Ela faz uso de um
 * parser para extenso de um n�mero dado como String.
 */
public class NumberManager {

	public static final String INITIAL_PROMPT = "Digite um n�mero natural entre 0 e 1000000000 (um bilh�o) :\n";
	public static final String ERROR_PROMPT = "Apenas n�meros naturais podem ser considerados v�lidos.\n";
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
		final int MIL_KEY = 1;
		final int MILHOES_KEY = 2;
		final int BILHAO_KEY = 3;
		getNumerationMap().put(MIL_KEY, Number.MIL);
		getNumerationMap().put(MILHOES_KEY, Number.MILHOES);
		getNumerationMap().put(BILHAO_KEY, Number.BILHAO);
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
		Number[] numberEnumValues = Number.values();
		String casaSelecionada;

		for (int index = 0; index < numberArray.length; index++) {

			boolean isLastNumber = (index == numberArray.length - 1);
			// N�mero corrente
			String numberNow = numberArray[index];

			if (Strings.isNullOrEmpty(numberNow)) {
				continue;
			}

			String unidade = "";
			String centena = "";
			String dezena = "";

			// As tres ultimas casas do n�mero n�o tem nomenclatura
			// especial(mil, milh�o.. etc)
			int casaSelecionadaIndex = (numberArray.length - 1) - index;
			casaSelecionada = index == numberArray.length - 1 ? ""
					: getNumerationMap().get(casaSelecionadaIndex).toString()
							+ ESPACO;

			// Apenas no caso do n�mero ser 1 milh�o se coloca no singular.
			if (numberNow.equals("1") && casaSelecionadaIndex == 2) {
				casaSelecionada = Number.MILHAO.toString();
			}
			switch (numberNow.length()) {
			// Caso do n�mero ter a casa das centenas
			case 3:
				// Caso especial do n�mero ser 100
				if (numberNow.trim().equals("100")) {
					centena = Number.CEM.toString() + ESPACO;
					break;
				}
				if (isSpecialCase(numberNow, 3)) {
					unidade = "";
					dezena = searchValue(numberEnumValues,
							numberNow.substring(1)).toString()
							+ ESPACO;
				} else {
					unidade = searchValue(numberEnumValues,
							numberNow.substring(2)).toString()
							+ ESPACO;
					unidade = unidade.trim().equals("zero") ? "" : unidade;
					dezena = searchValue(numberEnumValues,
							(numberNow.toCharArray()[1] + "d")).toString()
							+ ESPACO;
				}
				centena = searchValue(numberEnumValues,
						numberNow.toCharArray()[0] + "c").toString()
						+ ESPACO;
				break;

			case 2:
				// Caso do n�mero ter apenas a casa das dezenas.
				if (isSpecialCase(numberNow, 2)) {
					unidade = "";
					dezena = searchValue(numberEnumValues,
							numberNow.substring(0)).toString()
							+ ESPACO;
				} else {
					unidade = searchValue(numberEnumValues,
							numberNow.substring(1)).toString()
							+ ESPACO;
					dezena = searchValue(numberEnumValues,
							(numberNow.toCharArray()[0] + "d")).toString()
							+ ESPACO;
				}
				break;
			case 1:
				// Caso do n�mero ter apenas a casa das unidades.
				unidade = searchValue(numberEnumValues, numberNow.substring(0))
						.toString() + ESPACO;
				if (isLastNumber) {
					unidade = searchValue(numberEnumValues,
							numberNow.substring(0)).toString();
				}
				break;
			default:
				break;
			}

			String joinNumbers = join(centena, dezena, unidade);
			String outputToWrite = Strings.isNullOrEmpty(joinNumbers.trim()) ? ""
					: joinNumbers + casaSelecionada + ESPACO;

			numberFullOutPut.append(outputToWrite);
		}

		String formattedNumber = fixSpaces(numberFullOutPut.toString());
		return formattedNumber;
	}

	/**
	 * Formata o n�mero para ser exibido na tela, modularizando os espa�os entre
	 * as Strings
	 */
	private String fixSpaces(String string) {
		final int indexSpace = 1;
		String output = string.replaceAll("\\s+", " ");
		output = output.substring(0, output.length() - indexSpace);
		return output;
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
			if (!Strings.isNullOrEmpty(joinNumbersString.toString())
					&& !Strings.isNullOrEmpty(numbers[index].trim())) {
				joinNumbersString.append("e ");
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
			throws OperationNotSupportedException, IllegalArgumentException {
		if (numberInput.matches("[a-zA-Z]+.")) {
			throw new IllegalArgumentException("Letras n�o s�o v�lidas. "
					+ ERROR_PROMPT + INITIAL_PROMPT);
		}
		if (!numberInput.matches("[0-9]{1,9}")) {
			throw new OperationNotSupportedException(
					"A entrada deve ser n�o vazia. " + ERROR_PROMPT
							+ INITIAL_PROMPT);
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