package net.iessanclemente.dapw.katas.StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String txt) {
		int result = 0;
		if (txt.length() == 0)
			return result;
		
		String numbersString = txt;
		List<String> separators = new ArrayList<String>();
		separators.add(",");
		separators.add("\\n");
		if (txt.startsWith("//")) {
			separators.addAll(getSeparators(txt));
			numbersString = getNumbersString(txt);
		}

		List<String> negativesList = getNegatives(numbersString);
		if (negativesList.size() > 0) {
			String negatives= String.join(", ", negativesList);
			throw new IllegalArgumentException(
					"No se pueden pasar números negativos " + negatives
					+ ", no estan permitidos.");
		}				
		
		for (String number : getNumberList(numbersString, separators)) {								
			result += parseNumber(number);						
		}
		return result;
	}

	private int parseNumber(String numberString) {
		if (numberString.length() == 0) {
			throw new IllegalArgumentException(
					"No se pueden separar tokens vacíos en la cadena de suma");
		}		
		int n = Integer.parseInt(numberString);
		
		return (n>1000)? 0:n;
	}
	
	private List<String> getNegatives(String numbersTxt){
		List<String> negativesList = new ArrayList<String>();
		String stringPattern = "(?<negativo>-\\d+)";
		Pattern separatorPattern = Pattern.compile(stringPattern);
		Matcher partialMatcher = separatorPattern.matcher(numbersTxt);
		while (partialMatcher.find()) {
			negativesList.add(partialMatcher.group("negativo"));
		}
		return negativesList; 		
	}

	private List<String> getNumberList(String numbersTxt,
			List<String> separators) {
		StringBuffer sBSeparators = new StringBuffer();
		Iterator<String> it = separators.iterator();
		while (it.hasNext()) {
			sBSeparators.append(it.next());
			if (it.hasNext()) {
				sBSeparators.append("|");
			}
		}
		return Arrays.asList(numbersTxt.split(sBSeparators.toString()));
	}

	private String getNumbersString(String txt) {
		String wholeStringPattern = "((\\[)(?<separador>[^\\]]+)(\\]))+\\r\\n(?<numeros>(.|\n)*)";
		Pattern wholePattern = Pattern.compile(wholeStringPattern);
		Matcher wholeMatcher = wholePattern.matcher(txt);
		if (wholeMatcher.find()) {
			txt = wholeMatcher.group("numeros");
		}
		return txt;
	}

	private List<String> getSeparators(String txt) {
		List<String> separatorsList = new ArrayList<String>();
		String stringPattern = "(\\[)(?<separador>[^\\]]+)(\\])";
		Pattern separatorPattern = Pattern.compile(stringPattern);
		Matcher partialMatcher = separatorPattern.matcher(txt);
		while (partialMatcher.find()) {
			separatorsList.add(partialMatcher.group("separador"));
		}
		return separatorsList;
	}

}
