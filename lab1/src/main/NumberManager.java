package main;

import java.util.HashMap;

import javax.naming.OperationNotSupportedException;

import com.google.common.base.Strings;

/**
 * Essa classe é utilizada como um controlador de números. Ela faz uso de um
 * parser para extenso de um número dado como String. Atenção, muita parte do
 * código foi usada REGEX (regular expression) para facilitar o algoritmo, para
 * entender mais consultar. {@link http
 * ://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html}
 */
public class NumberManager {
	public static final String INITIAL_PROMPT = "Digite um número natural entre 0 e 1000000000 (um bilhão) :\n";
	public static final String ERROR_PROMPT = "Apenas números naturais podem ser considerados válidos.\n";
	private final String ESPACO = " ";

	private HashMap<Integer, Number> numerationMap;

	public NumberManager() {
		numerationMap = new HashMap<Integer, Number>();
		buildNumerationMap();

	}

	/**
	 * Constrói o mapa que guarda os nomes das casas dos números
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
	 * Transforma um número dado como String em forma de número Natural e
	 * retorna o nome do número por extenso.
	 * 
	 * @return O número por extenso.
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
			// Número corrente
			String currentNumber = numberArray[index];

			if (Strings.isNullOrEmpty(currentNumber)) {
				continue;
			}

			String unidade = "";
			String centena = "";
			String dezena = "";

			// As tres ultimas casas do número não tem nomenclatura
			// especial(mil, milhão.. etc)
			int casaSelecionadaIndex = (numberArray.length - 1) - index;

			// Caso não seja a última parte do número ( a qual não tem nome )
			// não se coloca nada.
			casaSelecionada = index == numberArray.length - 1 ? ""
					: getNumerationMap().get(casaSelecionadaIndex).toString()
							+ ESPACO;

			// Apenas no caso do número ser 1 milhão se coloca no singular.
			if (currentNumber.equals(Number.UM.getValue())
					&& casaSelecionadaIndex == 2) {
				casaSelecionada = Number.MILHAO.toString();
			}
			switch (currentNumber.length()) {
			case 1:
				// Caso do número ter apenas a casa das unidades.
				unidade = searchValue(numberEnumValues,
						currentNumber.substring(0)).toString();
				if (!isLastNumber) {
					unidade += ESPACO;
				}
				break;
			case 2:
				// Caso do número ter apenas a casa das dezenas.
				if (isSpecialCase(currentNumber, 2)) {
					unidade = "";
					dezena = searchValue(numberEnumValues,
							currentNumber.substring(0)).toString();
				} else {
					unidade = searchValue(numberEnumValues,
							currentNumber.substring(1)).toString()
							+ ESPACO;
					// Nao pode ter "zero" no nome do número
					unidade = unidade.trim()
							.equals(Number.ZERO.getNumberName()) ? "" : unidade;
					dezena = searchValue(
							numberEnumValues,
							(currentNumber.toCharArray()[0] + DEZENA_IDENTIFIER))
							.toString();
				}
				dezena += ESPACO;
				break;
			// Caso do número ter a casa das centenas
			case 3:
				// Caso especial do número ser 100
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
					// Nao pode ter "zero" no nome do número
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
			// Junta os números
			String joinNumbers = join(centena, dezena, unidade);
			// Coloca o nome da casa após a junção.
			String outputToWrite = Strings.isNullOrEmpty(joinNumbers.trim()) ? ""
					: joinNumbers + casaSelecionada + ESPACO;

			numberFullOutPut.append(outputToWrite);
		}
		String formattedNumber = fixSpaces(numberFullOutPut.toString());
		return formattedNumber;
	}

	/**
	 * Formata o número para ser exibido na tela, modularizando os espaços entre
	 * as Strings
	 */
	private String fixSpaces(String string) {
		final int indexSpace = 1;
		String output = string.replaceAll("\\s+", " ");
		output = output.substring(0, output.length() - indexSpace);
		return output;
	}

	/**
	 * Retorna se o número tem tratamento especial caso ele seja da casa das
	 * dezenas entre 10 e 20, que são nomes diferenciados.
	 * 
	 * @return true caso o número comece com 1.
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
	 * O Algoritmo que eu usei foi separar as casas de três em três, de forma
	 * que fique mais simples utilizar a nomenclatura. Ele faz uso de REGEX, O
	 * intuito do algoritmo é apenas retornar um array contendo as Strings dos
	 * números separadas.
	 * 
	 * @see { http
	 *      ://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.
	 *      html }
	 */
	private String[] separaNumeros(String number) {
		StringBuffer numberInputBuffer = new StringBuffer(number);
		String inverseNumber = numberInputBuffer.reverse().toString();
		// Esse regex significa quebrar o número de 3 em 3 algarismos
		String inverseNumberReplaced = inverseNumber.replaceAll("[0-9]{3}",
				"$0s");
		String reverseNumber = new StringBuffer(inverseNumberReplaced)
				.reverse().toString();
		String[] numberArray = reverseNumber.split("s");
		return numberArray;
	}

	/**
	 * Junta todos os números dados os colocando entre conectivos.
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
	 * Le o número e faz todo o tratamento para se transformar em extenso.
	 * 
	 * @throws OperationNotSupportedExcetion
	 *             Caso não seja um número passado como parâmetro ou ele não
	 *             esteja no intervalo permitido.
	 */
	public String readInput(String numberInput)
			throws OperationNotSupportedException, IllegalArgumentException {
		// Esse regex significa que a entrada não pode conter letras.
		if (numberInput.matches("[a-zA-Z]+.")) {
			throw new OperationNotSupportedException("Letras não são válidas. "
					+ ERROR_PROMPT + INITIAL_PROMPT);
		}
		// Esse regex quer dizer que a entrada pode ser seguida de quantos zeros
		// for a sua esquerda, ou pode começar com 1 e vir seguidos de 9 digitos
		// no máximo.
		if (!numberInput.matches("0*?((1(0){9})|([0-9]{1,9}))")) {
			throw new IllegalArgumentException("A entrada deve ser não vazia. "
					+ ERROR_PROMPT + INITIAL_PROMPT);
		}
		// Elimina os 0's à esquerda
		return parse(String.format("%d", Integer.parseInt(numberInput)));
	}

	public HashMap<Integer, Number> getNumerationMap() {
		return numerationMap;
	}

	public void setNumerationMap(HashMap<Integer, Number> numberMap) {
		numerationMap = numberMap;
	}
}