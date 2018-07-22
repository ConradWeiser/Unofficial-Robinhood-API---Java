package com.ampro.robinhood.endpoint.orders.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum OrderTransactionType implements RobinhoodEnum {
	BUY("buy"),
	SELL("sell");

	private final String value;
	OrderTransactionType(String s) { value = s; }

    /**
     * Parse an {@link OrderTransactionType} from a string
     * @param s The String to parse from
     * @return The parsed {@link OrderTransactionType} or null
     */
	public static OrderTransactionType parse(String s) {
	    switch (s) {
            case "buy": return BUY;
            case "sell": return SELL;
            default: return null;
        }
    }

	@Override
    public String toString() {
	    return this.value;
    }

	@Override
	public String getValue() {
		return value;
	}
}
