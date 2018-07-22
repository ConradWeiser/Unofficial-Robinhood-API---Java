package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

/**
 * Enum wrapper for RH "investor experience" levels
 * @author Conrad Weise, Jonathan Augustine
 */
public enum InvestmentExperience implements RobinhoodEnum {

	EXTENSIVE_INVESTMENT_EXPERIENCE("extensive_investment_exp"),
	GOOD_INVESTMENT_EXPERIENCE("good_investment_exp"),
	LIMITED_INVESTMENT_EXPERIENCE("limited_investment_exp"),
	NO_INVESTMENT_EXPERIENCE("no_investment_exp");

	private final String value;
	InvestmentExperience(String s) { this.value = s; }

    /**
     * Parse an {@link InvestmentExperience} from a string
     * @param s The String to parse from
     * @return The parsed {@link InvestmentExperience} or null
     */
	public static InvestmentExperience parse(String s) {
	    switch (s) {
            case "extensive_investment_exp":
                return EXTENSIVE_INVESTMENT_EXPERIENCE;
            case "good_investment_exp": return GOOD_INVESTMENT_EXPERIENCE;
            case "limited_investment_exp": return LIMITED_INVESTMENT_EXPERIENCE;
            case "no_investment_exp": return NO_INVESTMENT_EXPERIENCE;
            default: return null;
        }
    }

    @Override
    public String toString() { return this.value; }

    @Override
    public String getValue() { return this.value; }

}
