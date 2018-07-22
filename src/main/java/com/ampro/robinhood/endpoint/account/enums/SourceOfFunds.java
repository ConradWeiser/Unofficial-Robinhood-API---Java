package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum SourceOfFunds implements RobinhoodEnum {

	SAVINGS_PERSONAL_INCOME("savings_personal_income"),
	PENSION_RETIREMENT("pension_retirement"),
	INSURANCE_PAYOUT("insurance_payout"),
	INHERITANCE("inheritance"),
	GIFT("gift"),
	SALE_BUSINESS_OR_PROPERTY("sale_business_or_property"),
	OTHER("other");

	private final String value;
	SourceOfFunds(String s) { value = s; }

	/**
	 * Parse a {@link SourceOfFunds} from a String
	 * @param s The String to parse from
	 * @return The parsed {@link SourceOfFunds} or null
	 */
	public static SourceOfFunds parse(String s) {
		switch (s) {
			case "savings_personal_income": return SAVINGS_PERSONAL_INCOME;
			case "pension_retirement": return PENSION_RETIREMENT;
			case "insurance_payout": return INSURANCE_PAYOUT;
			case "inheritance": return INHERITANCE;
			case "gift": return GIFT;
			case "sale_business_or_property": return SALE_BUSINESS_OR_PROPERTY;
			case "other": return OTHER;
			default: return null;
		}
	}

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }

}
