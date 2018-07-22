package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum InvestmentObjective implements RobinhoodEnum {

	CAPITAL_PRESERVE_INVESTMENT_OBJECTIVE("cap_preserve_invest_obj"),
	INCOME_INVESTMENT_OBJECTIVE("income_invest_obj"),
	GROWTH_INVESTMENT_OBJECTIVE("growth_invest_obj"),
	SPECULATION_INVESTMENT_OBJECTIVE("speculation_invest_obj"),
	OTHER_INVESTMENT_OBJECTIVE("other_invest_obj");

	private final String value;
	InvestmentObjective(String s) { value = s; }

	/**
	 * Parse an {@link InvestmentObjective} from a String
	 * @param s The String to parse from
	 * @return The parsed {@link InvestmentObjective} or null
	 */
	public static InvestmentObjective parse(String s) {
		switch (s) {
			case "cap_preserve_invest_obj": return CAPITAL_PRESERVE_INVESTMENT_OBJECTIVE;
			case "income_invest_obj": return INCOME_INVESTMENT_OBJECTIVE;
			case "growth_invest_obj": return GROWTH_INVESTMENT_OBJECTIVE;
			case "speculation_invest_obj": return SPECULATION_INVESTMENT_OBJECTIVE;
			case "other_invest_obj": return OTHER_INVESTMENT_OBJECTIVE;
			default: return null;
		}
	}

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }

}
