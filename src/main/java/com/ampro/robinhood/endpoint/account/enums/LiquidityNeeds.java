package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

/**
 * An enum wrapper for a Robinhood account's "LiquidityNeeds" defined by the API.
 *
 * @author Jonathan Augustine
 */
public enum LiquidityNeeds implements RobinhoodEnum {

	NOT_IMPORTANT_LIQUIDITY_NEED("not_important_liq_need"),
	SOMEWHAT_IMPORTANT_LIQUIDITY_NEED("somewhat_important_liq_need"),
	VERY_IMPORTANT_LIQUIDITY_NEED("very_important_liq_need");

	private final String value;
	LiquidityNeeds(String s) { value = s; }

	/**
	 * Parse a {@link LiquidityNeeds} from a String.
	 * @param s The String to parse from
	 * @return The parsed {@link LiquidityNeeds} or null
	 */
	public static LiquidityNeeds parse(String s) {
		switch (s) {
			case "not_important_liq_need": return NOT_IMPORTANT_LIQUIDITY_NEED;
			case "somewhat_important_liq_need": return SOMEWHAT_IMPORTANT_LIQUIDITY_NEED;
			case "very_important_liq_need": return VERY_IMPORTANT_LIQUIDITY_NEED;
			default: return null;
		}
	}

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }
}
