package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.util.ChronoFormatter;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * An {@link ApiElement} containing information (or the URL location of
 * information) about a user account. To get this element, the
 * {@link com.ampro.robinhood.RobinhoodApi} instance must be logged in.
 *
 * @author Jonathan Augustine
 */
public class Account implements ApiElement {

	private boolean deactivated = false;

	private String updated_at;
	private String created_at;
	private MarginBalances margin_balances;
	private URL portfolio;
	private CashBalances cash_balances;
	private boolean withdrawl_halted;
	private float cash_available_for_withdrawl;
	private String type;
	private float sma;
	private boolean sweep_enabled;
	private boolean deposit_halted;
	private float buying_power;
	private URL user;
	private float max_ach_early_access_amount;
	private float cash_held_for_orders;
	private boolean only_position_closing_trades;
	private URL url;
	private URL positions;
	private float cash;
	private float sma_held_for_orders;
	private String account_number;
	private float uncleared_deposits;
	private float unsettled_funds;

	@Override
	public boolean requiresAuth() { return true; }

	/**
	 * @return the deactivated
	 */
	public boolean isDeactivated() {
		return deactivated;
	}

	public ZonedDateTime getCreatedAt() {
	    return ChronoFormatter.parseDefault(this.created_at);
    }

    public ZonedDateTime getUpdatedAt() {
	    return ChronoFormatter.parseDefault(this.updated_at);
    }

	/**
	 * @return the margin_balances
	 */
	public MarginBalances getMarginBalances() {
		return margin_balances;
	}
	/**
	 * @return the portfolio
	 */
	public URL getPortfolio() {
		return portfolio;
	}
	/**
	 * @return the cash_balances
	 */
	public CashBalances getCashBalances() {
		return cash_balances;
	}
	/**
	 * @return the withdrawl_halted
	 */
	public boolean isWithdrawlHalted() {
		return withdrawl_halted;
	}
	/**
	 * @return the cash_available_for_withdrawl
	 */
	public float getCashAvailableForWithdrawl() {
		return cash_available_for_withdrawl;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the sma
	 */
	public float getSma() {
		return sma;
	}
	/**
	 * @return the sweep_enabled
	 */
	public boolean isSweepEnabled() {
		return sweep_enabled;
	}
	/**
	 * @return the deposit_halted
	 */
	public boolean isDepositHalted() {
		return deposit_halted;
	}
	/**
	 * @return the buying_power
	 */
	public float getBuyingPower() {
		return buying_power;
	}
	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}
	/**
	 * @return the max_ach_early_access_amount
	 */
	public float getMaxAchEarlyAccessAmount() {
		return max_ach_early_access_amount;
	}
	/**
	 * @return the cash_held_for_orders
	 */
	public float getCashHeldForOrders() {
		return cash_held_for_orders;
	}
	/**
	 * @return the only_position_closing_trades
	 */
	public boolean isOnlyPositionClosingTrades() {
		return only_position_closing_trades;
	}
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * @return the positions
	 */
	public URL getPositions() {
		return positions;
	}
	/**
	 * @return the cash
	 */
	public float getCash() {
		return cash;
	}
	/**
	 * @return the sma_held_for_orders
	 */
	public float getSmaHeldForOrders() {
		return sma_held_for_orders;
	}
	/**
	 * @return the account_number
	 */
	public String getAccountNumber() {
		return account_number;
	}
	/**
	 * @return the uncleared_deposits
	 */
	public float getUnclearedDeposits() {
		return uncleared_deposits;
	}
	/**
	 * @return the unsettled_funds
	 */
	public float getUnsettledFunds() {
		return unsettled_funds;
	}

}
