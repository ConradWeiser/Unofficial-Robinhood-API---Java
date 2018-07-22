package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum TimeHorizon implements RobinhoodEnum {

	SHORT_TIME_HORIZON("short_time_horizon"),
	MEDIUM_TIME_HORIZON("med_time_horizon"),
	LONG_TIME_HORIZON("long_time_horizon");

	private final String value;
	TimeHorizon(String s) { value = s; }

	/**
	 * Parse a {@link TimeHorizon} from a String
	 * @param s The String to parse from
	 * @return The parsed {@link TimeHorizon} or null
	 */
	public static TimeHorizon parse(String s) {
		switch (s) {
			case "short_time_horizon": return SHORT_TIME_HORIZON;
			case "med_time_horizon": return MEDIUM_TIME_HORIZON;
			case "long_time_horizon": return LONG_TIME_HORIZON;
			default: return null;
		}
	}

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }

}
