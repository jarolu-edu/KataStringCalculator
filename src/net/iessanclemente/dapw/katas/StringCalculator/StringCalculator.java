package net.iessanclemente.dapw.katas.StringCalculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	private final static String COMA_SEPARATOR = ",";
	private final static String CR_SEPARATOR = "\\n";
	private static List<String> defaultSeparators;

	static {
		defaultSeparators = new ArrayList<String>();
		defaultSeparators.add(COMA_SEPARATOR);
		defaultSeparators.add(CR_SEPARATOR);
	}

	public static int add(String text) {
		int result = 0;
		if (text.length() == 0)
			return result;

		List<String> negativesList = new NamedRegexTokenizer(
				"(?<negativo>-\\d+)").getTokens(text, "negativo");

		if (negativesList.size() > 0) {
			String negatives = String.join(", ", negativesList);
			throw new IllegalArgumentException(
					"No se pueden pasar números negativos " + negatives
							+ ", no estan permitidos.");
		}

		List<String> separators = new NamedRegexTokenizer(
				"(\\[)(?<separador>[^\\]]+)(\\])").getTokens(text, "separador");
		separators.addAll(defaultSeparators);

		String numbersString = text;

		if (text.startsWith("//")) {
			List<String> numeros = new NamedRegexTokenizer(
					"((\\[)(?<separador>[^\\]]+)(\\]))+\\r\\n(?<numeros>(.|\n)*)")
					.getTokens(text, "numeros");
			numbersString = numeros.get(0);
		}

		List<Integer> numberList = NumberListTokenizer.getNumbers(
				numbersString, separators);

		for (Integer number : numberList) {
			result += (number > 1000) ? 0 : number;
		}
		return result;
	}

}
