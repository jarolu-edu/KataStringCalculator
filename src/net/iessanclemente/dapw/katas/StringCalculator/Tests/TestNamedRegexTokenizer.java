package net.iessanclemente.dapw.katas.StringCalculator.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.iessanclemente.dapw.katas.StringCalculator.NamedRegexTokenizer;

import org.junit.Before;
import org.junit.Test;

public class TestNamedRegexTokenizer {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void canTokenize() {
		NamedRegexTokenizer rt= new NamedRegexTokenizer("(?<negativo>-\\d+)");
		List<String> tokens=rt.getTokens("-1,1,-222,22-4","negativo");
		
		List<String> expected=new ArrayList<String>();
		expected.add("-1");
		expected.add("-222");
		expected.add("-4");
		assertEquals(expected,tokens);				
	}

}
