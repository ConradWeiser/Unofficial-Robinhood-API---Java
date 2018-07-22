package com.ampro.robinhood.endpoint.orders.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.orders.enums.OrderTransactionType;
import com.ampro.robinhood.endpoint.orders.enums.TimeInForce;
import com.ampro.robinhood.throwables.TickerNotFoundException;

/**
 * Make a Stop-trigger Limit Order.
 *
 * Created by SirensBell on 5/11/2017.
 */
public class MakeLimitStopOrder extends OrderMethod {

    private final String ticker;
    private final TimeInForce time;
    private final float limitPrice;
    private final int quantity;
    private final OrderTransactionType orderType;
    private final String tickerInstrumentUrl;
    private final float stopPrice;

    /**
     * @param ticker The ticker which the buy or sell order should be performed on
     * @param time The Enum representation for when this order should be made
     * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
     * @param quantity The number of shares you would like to buy or sell
     * @param orderType Which type of order is being made. A buy, or a sell
     * @param stopPrice The price at which the stop trigger converts the order
     *                      into a market order
     * @throws TickerNotFoundException The ticker supplied is not valid
     */
    public MakeLimitStopOrder(String ticker, TimeInForce time, float limitPrice,
                              int quantity, OrderTransactionType orderType,
                              float stopPrice, Configuration config)
    throws TickerNotFoundException {

        super(config);
        this.tickerInstrumentUrl = verifyTickerData(ticker);
        this.ticker = ticker;
        this.time = time;
        this.limitPrice = limitPrice;
        this.quantity = quantity;
        this.orderType = orderType;
        this.stopPrice = stopPrice;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        //Set the order parameters
        setOrderParameters();

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
        this.addFieldParameter("stop_price", this.stopPrice);
        this.addFieldParameter("trigger", "stop");
        this.addFieldParameter("quantity", String.valueOf(this.quantity));
        this.addFieldParameter("side", this.orderType.getValue());
    }

}
