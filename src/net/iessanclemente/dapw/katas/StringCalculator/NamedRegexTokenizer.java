package net.iessanclemente.dapw.katas.StringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedRegexTokenizer {
	
	private Pattern pattern;
	private Matcher matcher;				
	
	public NamedRegexTokenizer(String patternString) {		
		pattern = Pattern.compile(patternString);
		 
	}

	public List<String> getTokens(String text, String group) {
		List<String> resultList = new ArrayList<String>();
		matcher= pattern.matcher(text);
		
		while (matcher.find()) {
			resultList.add(matcher.group(group));
		}
		return resultList;
	}		

}
