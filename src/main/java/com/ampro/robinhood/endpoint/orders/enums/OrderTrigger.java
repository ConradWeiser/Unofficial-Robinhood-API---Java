package com.ampro.robinhood.endpoint.orders.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum OrderTrigger implements RobinhoodEnum {
	IMMEDIATE("immediate"),
	STOP("stop");

	private final String value;
	OrderTrigger(String s) { value = s; }

	/**
	 * Parse a String for an {@link OrderTrigger}
	 * @param s The String to parse
	 * @return The {@link OrderTrigger} or {@code null}
	 */
	public static OrderTrigger parse(String s) {
		switch (s) {
			case "immediate": return IMMEDIATE;
			case "stop": return STOP;
			default: return null;
		}
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
