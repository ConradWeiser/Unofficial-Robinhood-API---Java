package com.ampro.robinhood.endpoint.fundamentals.data;

import com.ampro.robinhood.endpoint.ApiElement;

import java.net.URL;

public class TickerFundamental implements ApiElement {

	/**
	 * The ticker price at market open
	 */
	private float open;
	/**
	 * The highest trade price since open
	 */
	private float high;
	/**
	 * The lowest trade price since open
	 */
	private float low;
	/**
	 * The volume of people trading this ticker since open
	 */
	private float volume;
	private float average_volume;
	/**
	 * The highest trade price in the last 52 weeks
	 */
	private float high_52_weeks;
	/**
	 * The lowest trade price in the last 52 weeks
	 */
	private float low_52_weeks;
	private float market_cap;
	private float dividend_yield;
	/**
	 * This value can be null sometimes
	 */
	private float pe_ratio;
	/**
	 * Long description of security suited for display
	 */
	private String description;
	private URL instrument;

	@Override
	public boolean requiresAuth() { return false; }

	/**
	 * @return the open
	 */
	public float getOpen() {
		return open;
	}
	/**
	 * @return the high
	 */
	public float getHigh() {
		return high;
	}
	/**
	 * @return the low
	 */
	public float getLow() {
		return low;
	}
	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}
	/**
	 * @return the average_volume
	 */
	public float getAverage_volume() {
		return average_volume;
	}
	/**
	 * @return the high_52_weeks
	 */
	public float getHigh_52_weeks() {
		return high_52_weeks;
	}
	/**
	 * @return the low_52_weeks
	 */
	public float getLow_52_weeks() {
		return low_52_weeks;
	}
	/**
	 * @return the market_cap
	 */
	public float getMarket_cap() {
		return market_cap;
	}
	/**
	 * @return the dividend_yield
	 */
	public float getDividend_yield() {
		return dividend_yield;
	}
	/**
	 * @return the pe_ratio
	 */
	public float getPe_ratio() {
		return pe_ratio;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the instrument
	 */
	public URL getInstrument() {
		return instrument;
	}








}
