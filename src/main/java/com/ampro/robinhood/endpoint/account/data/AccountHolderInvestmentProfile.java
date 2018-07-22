package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.account.enums.*;
import com.ampro.robinhood.util.ChronoFormatter;

import java.net.URL;
import java.time.ZonedDateTime;

public class AccountHolderInvestmentProfile implements ApiElement {

	private String annual_income;
	private String investment_experience;
	private String investment_objective;
	private String liquid_net_worth;
	private String liquidity_needs;
	private String risk_tolerance;
	private String source_of_funds;
	private boolean suitability_verified;
	private String tax_bracket;
	private String time_horizon;
	private String total_net_worth;

	private String updated_at;
	private URL user;

	@Override
	public boolean requiresAuth() { return true; }

	public ZonedDateTime getUpdatedAt() {
		return ChronoFormatter.parseDefault(this.updated_at);
	}

	/**
	 * @return the annual_income
	 */
	public String getAnnualIncome() {
		return annual_income;
	}

	/**
	 * @return the investment_experience as an enum value.
	 */
	public InvestmentExperience getInvestmentExperience() {
		return InvestmentExperience.parse(this.investment_experience);
	}

	/**
	 * @return the investment_objective as an enum value
	 */
	public InvestmentObjective getInvestmentObjective() {
		return InvestmentObjective.parse(this.investment_objective);
	}

	/**
	 * @return the liquid_net_worth
	 */
	public String getLiquidNetWorth() {
		return liquid_net_worth;
	}

	/**
	 * @return the liquidity_needs as an enum value
	 */
	public LiquidityNeeds getLiquidityNeeds() {
		return LiquidityNeeds.parse(this.liquidity_needs);
	}

	/**
	 * @return the risk_tolerance
	 */
	public RiskTolerance getRiskTolerance() {
		return RiskTolerance.parse(this.risk_tolerance);
	}

	/**
	 * @return the source_of_funds
	 */
	public SourceOfFunds getSourceOfFunds() {
		return SourceOfFunds.parse(this.source_of_funds);
	}

	/**
	 * @return the suitability_verified
	 */
	public boolean isSuitabilityVerified() {
		return suitability_verified;
	}

	/**
	 * @return the tax_bracket
	 */
	public String getTaxBracket() {
		return tax_bracket;
	}

	/**
	 * @return the time_horizon
	 */
	public TimeHorizon getTimeHorizon() {
		return TimeHorizon.parse(this.time_horizon);
	}

	/**
	 * @return the total_net_worth
	 */
	public String getTotalNetWorth() {
		return total_net_worth;
	}

	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}

}
