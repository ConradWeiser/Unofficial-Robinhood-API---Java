package com.ampro.robinhood.endpoint.orders.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.orders.data.SecurityOrder;
import com.ampro.robinhood.endpoint.orders.enums.OrderTransactionType;
import com.ampro.robinhood.endpoint.orders.enums.TimeInForce;
import com.ampro.robinhood.throwables.NotLoggedInException;
import com.ampro.robinhood.throwables.TickerNotFoundException;

public class MakeLimitOrder extends OrderMethod {

	private final String ticker;
	private final TimeInForce time;
	private final float limitPrice;
	private final int quantity;
	private final OrderTransactionType orderType;

	private String tickerInstrumentUrl;

	/**
	 * Method which returns a {@link SecurityOrder} after running a LIMIT order
	 * given the supplied parameters.
	 * @param ticker The ticker which the buy or sell order should be performed on
	 * @param time The Enum representation for when this order should be made
	 * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
	 * @param quantity The number of shares you would like to buy or sell
	 * @param orderType Which type of order is being made. A buy, or sell.
	 * @throws TickerNotFoundException Thrown when the ticker supplied to the
	 *                                   method is invalid.
	 * @throws NotLoggedInException  Thrown when this Robinhood Api instance is
	 *                      not logged into an account. Run the login method first.
	 */
	public MakeLimitOrder(String ticker, TimeInForce time, float limitPrice,
	                      int quantity, OrderTransactionType orderType,
	                      Configuration config)
    throws TickerNotFoundException {
        super(config);
        this.ticker = ticker;
        this.time = time;
        this.limitPrice = limitPrice;
        this.quantity = quantity;
        this.orderType = orderType;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        //Set the order parameters
        setOrderParameters();

        //Verify the ticker, and add the instrument URL to be used for later
        this.tickerInstrumentUrl = verifyTickerData(this.ticker);

    }

	@Override
	protected void setOrderParameters() {
		//Add the account URL for the currently logged in account
		this.addFieldParameter("account", this.config.getAccountUrl());
		this.addFieldParameter("instrument", this.tickerInstrumentUrl);
		this.addFieldParameter("symbol", this.ticker);
		this.addFieldParameter("type", "limit");
		this.addFieldParameter("time_in_force", this.time.toString());
		this.addFieldParameter("price", this.limitPrice);
		this.addFieldParameter("trigger", "immediate");
		this.addFieldParameter("quantity", String.valueOf(this.quantity));
		this.addFieldParameter("side", orderType.getValue());
	}

}
