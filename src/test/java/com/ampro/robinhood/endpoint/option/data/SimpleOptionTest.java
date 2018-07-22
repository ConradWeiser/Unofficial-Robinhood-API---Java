package com.ampro.robinhood.endpoint.option.data;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class SimpleOptionTest {
	
	@Test
	public void setUp() {
		SimpleOption option = new SimpleOption(
			"ABC", 
			"call", 
			new BigDecimal("2.0000"), 
			new BigDecimal("110.0000"), 
			new BigDecimal("100.0000"), 
			LocalDate.parse("2018-08-17"),
			new BigDecimal("100.0000")
		);
		
		assertEquals(
			"2 Aug 17, 2018 calls of ABC with a strike price $100 purchased for an average price of $110 (100 underlying shares)", 
			option.toString()
		);
	}
}
