package main;

import java.io.IOException;
import java.util.Scanner;

public class mainControl {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		NumberManager nm = new NumberManager();
		System.err.println(NumberManager.INITIAL_PROMPT);
		while (input.hasNext()) {
			try {
				System.out.println(nm.readInput(input.nextLine()));
			} catch (Exception exception) {
				System.err.println(exception.getMessage());
			}
		}
	}
}
