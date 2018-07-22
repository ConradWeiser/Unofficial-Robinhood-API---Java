package com.ampro.robinhood.endpoint.orders.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

/**
 * The time and/or duration an order will be active.
 *
 * @author Conrad Weiser, Jonathan Augustine
 */
public enum TimeInForce implements RobinhoodEnum {
	ON_MARKET_OPEN("opg"),
	IMMEDIATE_OR_CANCEL("ioc"),
	FILL_OR_KILL("fok"),
	GOOD_FOR_DAY("gfd"),
	GOOD_UNTIL_CANCELED("gtc");

	private final String value;
	TimeInForce(String s) { this.value = s; }

    /**
     * Parse a {@link TimeInForce} enum from a Robinhood API formatted string
     * @param s The string to parse from
     * @return The {@link TimeInForce} parsed or null
     */
	public static TimeInForce parse(String s) {
        switch (s) {
            case "opg": return ON_MARKET_OPEN;
            case "ioc": return IMMEDIATE_OR_CANCEL;
            case "fok": return FILL_OR_KILL;
            case "gfd": return GOOD_FOR_DAY;
            case "gtc": return GOOD_UNTIL_CANCELED;
            default: return null;
        }
    }

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }
}
