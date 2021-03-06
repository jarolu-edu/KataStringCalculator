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
						if(t.startsWith("0x")){
							return Integer.parseInt(t.substring(2),16);
						}
						if(t.startsWith("0b")){
							return Integer.parseInt(t.substring(2),2);
						}
						if(t.startsWith("\\u")){
							return Integer.parseInt(t.substring(2),8);
						}
						return Integer.parseInt(t);
					}
				}).collect(Collectors.toList());

	}

}
