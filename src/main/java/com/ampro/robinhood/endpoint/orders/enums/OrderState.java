package com.ampro.robinhood.endpoint.orders.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum OrderState implements RobinhoodEnum {
	QUEUED("queued"),
	UNCONFIRMED("unconfirmed"),
	CONFIRMED("confirmed"),
	PARTIALLY_FILLED("partially_filled"),
	FILLED("filled"),
	REJECTED("rejected"),
	CANCELED("canceled"),
	FAILED("failed");

	private final String value;
	OrderState(String s) { value = s; }

	/**
	 * Parse an {@link OrderState} from a string.
	 * @param string The Robinhood API formatted orderstate String
	 * @return The parsed {@link OrderState} else null
	 */
	public static OrderState parse(String string) {
		switch(string) {
			case "queued": return OrderState.QUEUED;
			case "unconfirmed": return OrderState.UNCONFIRMED;
			case "confirmed": return OrderState.CONFIRMED;
			case "partially_filled": return OrderState.PARTIALLY_FILLED;
			case "filled": return OrderState.FILLED;
			case "rejected": return OrderState.REJECTED;
			case "canceled": return OrderState.CANCELED;
			case "failed": return OrderState.FAILED;
			default: return null;
		}
	}

	@Override
    public String toString() {
	    return this.value;
    }

    /**
     * Get the Robinhood API's string that corresponds to this enum
     *
     * @return The enum as a Robinhood JSON formatted string.
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
