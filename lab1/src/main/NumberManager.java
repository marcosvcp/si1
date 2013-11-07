package main;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

public class NumberManager {

	public static final String INITIAL_PROMPT = "";
	public static final String ERROR_PROMPT = "";

	public static String parse(String number) {
		StringBuffer numberInputBuffer = new StringBuffer(number);
		String inverseNumber = numberInputBuffer.reverse().toString();
		String inverseNumberReplaced = inverseNumber.replaceAll("[0-9]{3}",
				"$0s");
		String reverseNumber = new StringBuffer(inverseNumberReplaced)
				.reverse().toString();
		String[] numberArray = reverseNumber.split("s");
		for(int index = numberArray.length - 1 ; index >= 0; index--) {
			
		}
		return "";
	}

	public static String join(Number... numbers) {
		StringBuilder joinNumbersString = new StringBuilder("");
		for (Number number : numbers) {
			joinNumbersString.append(number.toString()
					+ Number.CONECTIVO.toString());
		}
		return joinNumbersString.toString();
	}

	public static String readInput(String numberInput)
			throws OperationNotSupportedException {
		if (!numberInput.matches("[0-9]{1,9}")) {
			throw new OperationNotSupportedException(
					"A entrada deve ser não vazia e apenas números naturais entre 0 e 1000000000 (um bilhão)");
		}
		return parse(numberInput);
	}
}
