package net.iessanclemente.dapw.katas.StringCalculator.Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.iessanclemente.dapw.katas.StringCalculator.NumberListTokenizer;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestNumberListTokenizer {
		
	private List<String> separators;
	
	@Before
	public void setUp() throws Exception {		
		separators=new ArrayList<String>();
		separators.add(",");
		separators.add("\\n");
	}

	@Test
	public void oneNumberReturnsTheNumber() {		
		List<Integer> result=NumberListTokenizer.getNumbers("2",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		assertEquals(expected,result);
	}
	
	
	@Test
	public void oneTwoNumbersReturnsTheNumbers() {		
		List<Integer> result=NumberListTokenizer.getNumbers("2,1",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		assertEquals(expected,result);
	}
	
	@Test
	public void oneTwoNumbersReturnsTheNumbersCR_Separator() {		
		List<Integer> result=NumberListTokenizer.getNumbers("2\n1",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		assertEquals(expected,result);
	}
	
	@Test
	public void mixedSeparatorsAlsoWorks() {		
		List<Integer> result=NumberListTokenizer.getNumbers("2\n1,4",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		expected.add(new Integer(4));
		assertEquals(expected,result);
	}
	
	
	@Test
	public void definedSeparatorsAlsoWorks() {		
		String [] newSeparators= {"@","#"};								
		separators.addAll(Arrays.asList(newSeparators));
		List<Integer> result=NumberListTokenizer.getNumbers("2\n1,4@5#6",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		expected.add(new Integer(4));
		expected.add(new Integer(5));
		expected.add(new Integer(6));
		assertEquals(expected,result);
	}
	
	
	@Test
	public void hexNumbersSuported() {		
		String [] newSeparators= {"@","#"};								
		separators.addAll(Arrays.asList(newSeparators));
		List<Integer> result=NumberListTokenizer.getNumbers("0x2\n0x1,0xF",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		expected.add(new Integer(15));		
		assertEquals(expected,result);
	}
	
	@Test
	public void octalNumbersSuported() {		
		String [] newSeparators= {"@","#"};								
		separators.addAll(Arrays.asList(newSeparators));
		List<Integer> result=NumberListTokenizer.getNumbers("0x2\n0x1,\\u0017",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(1));
		expected.add(new Integer(15));		
		assertEquals(expected,result);
	}

	
	@Test
	public void binaryNumbersSuported() {		
		String [] newSeparators= {"@","#"};								
		separators.addAll(Arrays.asList(newSeparators));
		List<Integer> result=NumberListTokenizer.getNumbers("0x2\n0b011,\\u0017",separators);
		
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(new Integer(2));
		expected.add(new Integer(3));
		expected.add(new Integer(15));		
		assertEquals(expected,result);
	}

}
