package net.iessanclemente.dapw.katas.StringCalculator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumberListTokenizer {	
	
	public static List<Integer> getNumbers(String text, List<String> separators) {
		StringBuffer sBSeparators = new StringBuffer();
		Iterator<String> it = separators.iterator();
		while (it.hasNext()) {
			String sep=it.next();
			sep=sep.replaceAll("\\*", "\\\\*");
			sep=sep.replaceAll("\\+", "\\\\+");
			sBSeparators.append(sep);
			if (it.hasNext()) {
				sBSeparators.append("|");
			}
		}

		return Arrays.asList(text.split(sBSeparators.toString())).stream()
				.map(new Function<String, Integer>() {
					@Override
					public Integer apply(String t) {
						// TODO Auto-generated method stub
						return Integer.parseInt(t);
					}
				}).collect(Collectors.toList());

	}

}
