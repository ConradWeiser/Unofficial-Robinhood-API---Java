package com.ampro.robinhood.endpoint.orders.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.orders.enums.OrderState;
import com.ampro.robinhood.util.ChronoFormatter;

import java.net.URL;
import java.time.ZonedDateTime;

public class SecurityOrder implements ApiElement {

	private String updated_at;
	private Execution[] executions;
	/**
	 * Total fees incurred. Normally this is 0.00 - Robinhood just likes to
	 * flaunt the fact
	 */
	private float fees;

	/**
	 * If this is not NULL, you can POST to this URL to cancel the order
	 */
	private URL cancel;

	/**
	 * The internal ID of this order
	 */
	private String id;

	/**
	 * The number of shares which have executed so far
	 */
	private float cumulative_quantity;

	/**
	 * A String description of why the order was rejected. This can also be NULL
	 */
	private String reject_reason;

	/**
	 * The state of the order. This is returned as an {@link OrderState} enum in the getter
	 */
	private String state;

	private String last_transaction_at;

	private String client_id;

	/** A link to this order with up to date information */
	private URL url;

	private String created_at;

	/** A link to positions for this account with this instrument */
	private URL position;

	/** Average price for all shares executed so far */
	private float average_price;

	/**
	 * Should this execute after the exchanges are closed?
	 * Only really returns true if you have Robinhood Gold
	 */
	private boolean extended_hours;

	/** Do you have pattern day trading checking enabled? */
	private boolean override_day_trade_checks;

	private boolean override_dtbp_checks;

	@Override
	public boolean requiresAuth() { return true; }

	public ZonedDateTime getUpdatedAt() {
		return ChronoFormatter.parseDefault(this.updated_at);
	}

	public ZonedDateTime getCreatedAt() {
		return ChronoFormatter.parseDefault(this.created_at);
	}

	public ZonedDateTime getLastTransactionAt() {
		return ChronoFormatter.parseDefault(this.last_transaction_at);
	}

	/**
	 * @return the executions
	 */
	public Execution[] getExecutions() {
		return executions;
	}

	/**
	 * @return the fees
	 */
	public float getFees() {
		return fees;
	}

	/**
	 * @return the cancel
	 */
	public URL getCancel() {
		return cancel;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the cumulative_quantity
	 */
	public float getCumulativeQuantity() {
		return cumulative_quantity;
	}

	/**
	 * @return the reject_reason
	 */
	public String getRejectReason() {
		return reject_reason;
	}

	/**
	 * @return the state as an ENUM value representing each possible response. See {@link OrderState}
	 */
	public OrderState getTransactionState() {
		return OrderState.parse(this.state);
	}

	/**
	 * @return the state of the transaction as a readable string.
	 */
	public String getTransactionStateAsString() {
		return this.state;
	}

	/**
	 * @return the client_id
	 */
	public String getClientId() {
		return client_id;
	}

	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @return the position
	 */
	public URL getPosition() {
		return position;
	}

	/**
	 * @return the average_price
	 */
	public float getAveragePrice() {
		return average_price;
	}

	/**
	 * @return the extended_hours
	 */
	public boolean isExtendedHours() {
		return extended_hours;
	}

	/**
	 * @return the override_day_trade_checks
	 */
	public boolean doesOverrideDayTradeChecks() {
		return override_day_trade_checks;
	}

	/**
	 * @return the override_dtbp_checks
	 */
	public boolean doesOverrideDtbpChecks() {
		return override_dtbp_checks;
	}

}
