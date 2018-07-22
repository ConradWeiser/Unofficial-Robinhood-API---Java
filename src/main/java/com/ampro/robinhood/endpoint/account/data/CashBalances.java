package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.util.ChronoFormatter;

import java.time.ZonedDateTime;

public class CashBalances implements ApiElement {

	private float cash_held_for_orders;

	private String created_at;
	private float cash;
	private float buying_power;

	private String updated_at;

	private float cash_available_for_withdrawl;
	private float uncleared_deposits;
	private float unsettled_funds;

	@Override
	public boolean requiresAuth() { return true; }

	public ZonedDateTime getUpdatedAt() {
		return ChronoFormatter.parseDefault(this.updated_at);
	}

	public ZonedDateTime getCreatedAt() {
		return ChronoFormatter.parseDefault(this.created_at);
	}

	/**
	 * @return the cash_held_for_orders
	 */
	public float getCash_held_for_orders() {
		return cash_held_for_orders;
	}
	/**
	 * @return the cash
	 */
	public float getCash() {
		return cash;
	}
	/**
	 * @return the buying_power
	 */
	public float getBuying_power() {
		return buying_power;
	}
	/**
	 * @return the cash_available_for_withdrawl
	 */
	public float getCash_available_for_withdrawl() {
		return cash_available_for_withdrawl;
	}
	/**
	 * @return the uncleared_deposits
	 */
	public float getUncleared_deposits() {
		return uncleared_deposits;
	}
	/**
	 * @return the unsettled_funds
	 */
	public float getUnsettled_funds() {
		return unsettled_funds;
	}

}
