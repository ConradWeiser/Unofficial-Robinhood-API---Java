package com.ampro.robinhood.endpoint.account.enums;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

/**
 * An account's risk tolerance.
 * @author Jonathan Augustine
 */
public enum RiskTolerance implements RobinhoodEnum {

	LOW_RISK_TOLERANCE("low_risk_tolerance"),
	MED_RISK_TOLERANCE("med_risk_tolerance"),
	HIGH_RISK_TOLERANCE("high_risk_tolerance");

	private final String value;
	RiskTolerance(String s) { value = s; }

	/**
	 * Parse a {@link RiskTolerance} from a String.
	 * @param s The String to parse
	 * @return The parsed {@link RiskTolerance} or null
	 */
	public static RiskTolerance parse(String s) {
		switch (s) {
			case "low_risk_tolerance": return LOW_RISK_TOLERANCE;
			case "med_risk_tolerance": return MED_RISK_TOLERANCE;
			case "high_risk_tolerance": return HIGH_RISK_TOLERANCE;
			default: return null;
		}
	}

	@Override
	public String toString() { return this.value; }

	@Override
	public String getValue() { return this.value; }
}
