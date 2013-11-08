package main;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.google.common.base.Strings;

/**
 * Essa classe � utilizada como um controlador de n�meros. Ela faz uso de um
 * parser para extenso de um n�mero dado como String. Aten��o, muita parte do
 * c�digo foi usada REGEX (regular expression) para facilitar o algoritmo, para
 * entender mais consultar. {@link http
 * ://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html}
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
		final String DEZENA_IDENTIFIER = "d";
		final String CENTENA_IDENTIFIER = "c";

		for (int index = 0; index < numberArray.length; index++) {

			boolean isLastNumber = (index == numberArray.length - 1);
			// N�mero corrente
			String currentNumber = numberArray[index];

			if (Strings.isNullOrEmpty(currentNumber)) {
				continue;
			}

			String unidade = "";
			String centena = "";
			String dezena = "";

			// As tres ultimas casas do n�mero n�o tem nomenclatura
			// especial(mil, milh�o.. etc)
			int casaSelecionadaIndex = (numberArray.length - 1) - index;

			// Caso n�o seja a �ltima parte do n�mero ( a qual n�o tem nome )
			// n�o se coloca nada.
			casaSelecionada = index == numberArray.length - 1 ? ""
					: getNumerationMap().get(casaSelecionadaIndex).toString()
							+ ESPACO;

			// Apenas no caso do n�mero ser 1 milh�o se coloca no singular.
			if (currentNumber.equals(Number.UM.getValue())
					&& casaSelecionadaIndex == 2) {
				casaSelecionada = Number.MILHAO.toString();
			}
			switch (currentNumber.length()) {
			case 1:
				// Caso do n�mero ter apenas a casa das unidades.
				unidade = searchValue(numberEnumValues,
						currentNumber.substring(0)).toString();
				if (!isLastNumber) {
					unidade += ESPACO;
				}
				break;
			case 2:
				// Caso do n�mero ter apenas a casa das dezenas.
				if (isSpecialCase(currentNumber, 2)) {
					unidade = "";
					dezena = searchValue(numberEnumValues,
							currentNumber.substring(0)).toString();
				} else {
					unidade = searchValue(numberEnumValues,
							currentNumber.substring(1)).toString()
							+ ESPACO;
					// Nao pode ter "zero" no nome do n�mero
					unidade = unidade.trim()
							.equals(Number.ZERO.getNumberName()) ? "" : unidade;
					dezena = searchValue(
							numberEnumValues,
							(currentNumber.toCharArray()[0] + DEZENA_IDENTIFIER))
							.toString();
				}
				dezena += ESPACO;
				break;
			// Caso do n�mero ter a casa das centenas
			case 3:
				// Caso especial do n�mero ser 100
				if (currentNumber.trim().equals("100")) {
					centena = Number.CEM.toString() + ESPACO;
					break;
				}
				if (isSpecialCase(currentNumber, 3)) {
					unidade = "";
					dezena = searchValue(numberEnumValues,
							currentNumber.substring(1)).toString();
				} else {
					unidade = searchValue(numberEnumValues,
							currentNumber.substring(2)).toString()
							+ ESPACO;
					// Nao pode ter "zero" no nome do n�mero
					unidade = unidade.trim()
							.equals(Number.ZERO.getNumberName()) ? "" : unidade;
					dezena = searchValue(
							numberEnumValues,
							(currentNumber.toCharArray()[1] + DEZENA_IDENTIFIER))
							.toString();
				}
				dezena += ESPACO;
				centena = searchValue(numberEnumValues,
						currentNumber.toCharArray()[0] + CENTENA_IDENTIFIER)
						.toString() + ESPACO;
				break;
			default:
				break;
			}
			// Junta os n�meros
			String joinNumbers = join(centena, dezena, unidade);
			// Coloca o nome da casa ap�s a jun��o.
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
		final int CASA_DEZENA = 2;
		return (numberNow.toCharArray()[length - CASA_DEZENA] == ('1'));
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
		// Esse regex significa quebrar o n�mero de 3 em 3 algarismos
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
		final String CONECTIVO = "e ";
		StringBuilder joinNumbersString = new StringBuilder("");
		for (int index = 0; index < numbers.length; index++) {
			if (!Strings.isNullOrEmpty(joinNumbersString.toString())
					&& !Strings.isNullOrEmpty(numbers[index].trim())) {
				joinNumbersString.append(CONECTIVO);
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
		// Esse regex significa que a entrada n�o pode conter letras.
		if (numberInput.matches("[a-zA-Z]+.")) {
			throw new OperationNotSupportedException("Letras n�o s�o v�lidas. "
					+ ERROR_PROMPT + INITIAL_PROMPT);
		}
		// Esse regex quer dizer que a entrada pode ser seguida de quantos zeros
		// for a sua esquerda, ou pode come�ar com 1 e vir seguidos de 9 digitos
		// no m�ximo.
		if (!numberInput.matches("0*?((1(0){9})|([0-9]{1,9}))")) {
			throw new IllegalArgumentException("A entrada deve ser n�o vazia. "
					+ ERROR_PROMPT + INITIAL_PROMPT);
		}
		// Elimina os 0's � esquerda
		return parse(String.format("%d", Integer.parseInt(numberInput)));
	}

	public HashMap<Integer, Number> getNumerationMap() {
		return numerationMap;
	}

	public void setNumerationMap(HashMap<Integer, Number> numberMap) {
		numerationMap = numberMap;
	}
}