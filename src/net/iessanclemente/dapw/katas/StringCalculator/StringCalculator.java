package net.iessanclemente.dapw.katas.StringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String numbersString) {
		if (numbersString.length() == 0)
			return 0;
		if (numbersString.length() == 1)
			return Integer.parseInt(numbersString);

		int result = 0;
		String[] numbersList = getNumbers(numbersString);
		StringBuffer negatives = new StringBuffer();
		for (String number : numbersList) {
			if (number.length() == 0) {
				throw new IllegalArgumentException(
						"No se pueden separar tokens vacíos en la cadena de suma");
			}					
			int n=Integer.parseInt(number);
			
			if(n>0){
				if(n<=1000){
					result +=n ;					
				}
			}else{
				negatives.append(", ").append(number);
			}
		}
		if(negatives.toString().length()>0){			
			throw new IllegalArgumentException(
					"No se pueden pasar números negativos"+negatives+", no estan permitidos.");
		}
		return result;
	}

	private String[] getNumbers(String numbersString) {

		StringBuffer separators = new StringBuffer();
		separators.append(",")
		.append("|")
		.append("\\n");

		if (numbersString.startsWith("//")) {			
			String stringPattern = "(\\[)(?<separador>[^\\]]+)(\\])";
			Pattern separatorPattern = Pattern.compile(stringPattern);
			Matcher partialMatcher = separatorPattern.matcher(numbersString);
			while (partialMatcher.find()) {
				String aux = partialMatcher.group("separador");
				separators.append("|").append(aux);				
			}
			
			String wholeStringPattern="((\\[)(?<separador>[^\\]]+)(\\]))+\\r\\n(?<numeros>(.|\n)*)";
			Pattern wholePattern=Pattern.compile(wholeStringPattern);
			Matcher wholeMatcher= wholePattern.matcher(numbersString);
			if(wholeMatcher.find()){
				numbersString=wholeMatcher.group("numeros");
			}
		}		
		return numbersString.split(separators.toString());		
	}

}
