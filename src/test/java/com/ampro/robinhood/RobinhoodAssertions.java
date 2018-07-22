package com.ampro.robinhood;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class RobinhoodAssertions {

	private RobinhoodAssertions() {}

	public static void assertDateTimeEquals(int year, int month, int day,
	                                        int hour, int minute, int second,
	                                        int nano, ZoneOffset offset,
	                                        ZonedDateTime actual) {
		assertEquals(year, actual.getYear());
		assertEquals(month, actual.getMonthValue());
		assertEquals(day, actual.getDayOfMonth());
		assertEquals(hour, actual.getHour());
		assertEquals(minute, actual.getMinute());
		assertEquals(second, actual.getSecond());
		assertEquals(nano, actual.getNano());
		assertEquals(offset, actual.getOffset());
	}

	public static void assertDateEquals(int year, int month, int day, LocalDate actual) {
		assertEquals(year, actual.getYear());
		assertEquals(month, actual.getMonthValue());
		assertEquals(day, actual.getDayOfMonth());
	}
}
