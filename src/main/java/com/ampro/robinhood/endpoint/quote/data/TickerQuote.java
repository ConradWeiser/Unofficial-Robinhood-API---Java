package com.ampro.robinhood.endpoint.quote.data;

import com.ampro.robinhood.endpoint.ApiElement;

/**
 * Created by SirensBell on 6/19/2017.
 */
public class TickerQuote implements ApiElement {

    /** The ask price. */
    private float ask_price;

    /** The ask size. */
    private int ask_size;

    /** The bid price. */
    private float bid_price;

    /** The big size. */
    private int big_size;

    /** The last trade price. */
    private float last_trade_price;

    /** The last extended hours trade price. */
    private float last_extended_hours_trade_price;

    /** The previous close. */
    private float previous_close;

    /** The adjusted previous close. */
    private float adjusted_previous_close;

    /** The previous close date. */
    private String previous_close_date;

    /** The symbol. */
    private String symbol;

    /** The trading halted. */
    private boolean trading_halted;

    /** The updated at. */
    private String updated_at;

    /** The instrument. */
    private String instrument;

    /* (non-Javadoc)
     * @see com.ampro.robinhood.endpoint.ApiElement#requiresAuth()
     */
    @Override
    public boolean requiresAuth() { return false; }

    /**
     * Gets the ask price.
     *
     * @return the ask price
     */
    public float getAskPrice() {
        return ask_price;
    }

    /**
     * Gets the ask size.
     *
     * @return the ask size
     */
    public int getAskSize() {
        return ask_size;
    }

    /**
     * Gets the bid price.
     *
     * @return the bid price
     */
    public float getBidPrice() {
        return bid_price;
    }

    /**
     * Gets the big size.
     *
     * @return the big size
     */
    public int getBigSize() {
        return big_size;
    }

    /**
     * Gets the last trade price.
     *
     * @return the last trade price
     */
    public float getLastTradePrice() {
        return last_trade_price;
    }

    /**
     * Gets the last extended hours trade price.
     *
     * @return the last extended hours trade price
     */
    public float getLastExtendedHoursTradePrice() {
        return last_extended_hours_trade_price;
    }

    /**
     * Gets the previous close.
     *
     * @return the previous close
     */
    public float getPreviousClose() {
        return previous_close;
    }

    /**
     * Gets the adjusted previous close.
     *
     * @return the adjusted previous close
     */
    public float getAdjustedPreviousClose() {
        return adjusted_previous_close;
    }

    /**
     * Gets the previous close date.
     *
     * @return the previous close date
     */
    public String getPreviousCloseDate() {
        return previous_close_date;
    }

    /**
     * Gets the symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Checks if is trading halted.
     *
     * @return true, if is trading halted
     */
    public boolean isTradingHalted() {
        return trading_halted;
    }

    /**
     * Gets the updated at.
     *
     * @return the updated at
     */
    public String getUpdatedAt() {
        return updated_at;
    }

    /**
     * Gets the instrument id.
     *
     * @return the instrument id
     */
    public String getInstrumentId() {
        return instrument == null ? "" : instrument.split("/")[4];
    }

}
