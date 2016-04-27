package net.iessanclemente.dapw.katas.StringCalculator.Tests;
import static org.junit.Assert.assertEquals;
import net.iessanclemente.dapw.katas.StringCalculator.StringCalculator;

import org.junit.Before;
import org.junit.Test;


public class TestStringCalculator {	

	@Before
	public void setUp(){
				
	}
	
	
	@Test
	public void testEmptyReturnsCero() {
		int result = StringCalculator.add("");
		assertEquals(0, result);
	}	
	
	@Test
	public void testOneNumberReturnsTheNumber() {		
		int shouldBeOne = StringCalculator.add("1");		
		int shouldBeTwo = StringCalculator.add("2");
		assertEquals(1, shouldBeOne);
		assertEquals(2, shouldBeTwo);
	}
	
	@Test
	public void testTwoNumbersReturnTheSumOfThem() {		
		int shouldBeThree = StringCalculator.add("1,2");				
		int shouldBeFour = StringCalculator.add("2,2");
		assertEquals(3, shouldBeThree);		
		assertEquals(4, shouldBeFour);
	}
	 
	@Test
	public void testThreeNumbersReturnTheSumOfThem() {		
		int shouldBeSix = StringCalculator.add("1,2,3");				
		int shouldBeNine = StringCalculator.add("2,2,5");
		assertEquals(6, shouldBeSix);		
		assertEquals(9, shouldBeNine);
	}	
	
	@Test 
	public void testNewLineSeparatorAlsoWorks(){
		int shouldBeSix = StringCalculator.add("1,2\n3");				
		int shouldBeNine = StringCalculator.add("2\n2,5");
		assertEquals(6, shouldBeSix);		
		assertEquals(9, shouldBeNine);
		//int shouldNotWork = sc.add("2,\n2,5");
		//assertEquals(9, shouldNotWork);
	}
	
	
	@Test 
	public void testSupportMultipleSeparatorsAlsoWorks(){
		int shouldBeEight = StringCalculator.add("//[;][@]\r\n1;4\n3");				
		int shouldBeEleven = StringCalculator.add("//[#]\r\n2#2,5\n2");
		assertEquals(8, shouldBeEight);		
		assertEquals(11, shouldBeEleven);
	}
	
//	@Test 
//	public void testNegativesShouldNotWorks(){
//		int shouldThrowAnException = sc.add("-11,2\n-3");						
//	}
	
	
	@Test 
	public void testIgnoreBiggerThan1000(){
		int shouldBeSeven = StringCalculator.add("//[;][@]\r\n1001;4\n3");				
		int shouldBeAThousandAndNine = StringCalculator.add("//[#]\r\n2#1000,5\n2");
		assertEquals(7, shouldBeSeven);		
		assertEquals(1009, shouldBeAThousandAndNine);
	}
	
	
	@Test 
	public void testDelimitersWithAnyLengthAlsoWorks(){
		int shouldBeSeven = StringCalculator.add("//[;;;;;][@]\r\n1001;;;;;4\n3");				
		int shouldBeAThousandAndNine = StringCalculator.add("//[#][@@]\r\n2#1000,5@@2");
		assertEquals(7, shouldBeSeven);		
		assertEquals(1009, shouldBeAThousandAndNine);
	}
	
	@Test 
	public void testStringCalculatorSuportsHex(){
		int shouldTwenty = StringCalculator.add("0xF,0x5");									
		assertEquals(20, shouldTwenty);
	}
	
	

}
