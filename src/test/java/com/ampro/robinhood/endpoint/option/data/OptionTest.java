package com.ampro.robinhood.endpoint.option.data;

import com.ampro.robinhood.throwables.NonSimpleOptionException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class OptionTest {

	private Option option;

	@Before
	public void setUp() {
		option = new Option();
	}

	@Test
	public void emptyOptionIsNotSimpleOption() {
		assertFalse(option.isSimpleOption());
	}

	@Test
	public void emptyLegsIsNotSimpleOption() {
		option.setLegs(new ArrayList<>());
		assertFalse(option.isSimpleOption());
	}

	@Test
	public void singleLegIsSimpleOption() {
		Leg leg = new Leg();
		option.setLegs(Collections.singletonList(leg));
		assertTrue(option.isSimpleOption());
	}

	@Test
	public void twoLegsIsNotSimpleOption() {
		Leg leg1 = new Leg();
		Leg leg2 = new Leg();
		option.setLegs(Arrays.asList(leg1, leg2));
		assertFalse(option.isSimpleOption());
	}

	@Test
	public void simpleOptionHasCorrectData() throws NonSimpleOptionException {

		Leg leg = new Leg();
		leg.setExpirationDate("2018-01-01");
		leg.setStrikePrice("20.0000");

		option.setSymbol("ABC");
		option.setQuantity("5.0000");
		option.setAverageOpenPrice("10.0000");
		option.setTradeValueMultiplier("0");
		option.setLegs(Collections.singletonList(leg));

		SimpleOption simpleOption = option.asSimpleOption();

		assertTrue(option.isSimpleOption());
		assertEquals(option.getSymbol(), simpleOption.getSymbol());
		assertEquals(option.getQuantity(), simpleOption.getQuantity());
		assertEquals(option.getAverageOpenPrice(), simpleOption.getAverageOpenPrice());
		assertEquals(leg.getExpirationDate(), simpleOption.getExpirationDate());
		assertEquals(leg.getStrikePrice(), simpleOption.getStrikePrice());
	}
}
