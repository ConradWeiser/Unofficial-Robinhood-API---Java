package com.ampro.robinhood.endpoint.option.data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.throwables.NonSimpleOptionException;
import com.ampro.robinhood.util.ChronoFormatter;

/**
 * Abstraction representing a single Robinhood investment option. This option
 * mirrors the options domain object returned from the
 * {@code /options/...} Robinhood Representational State
 * Transfer (REST) endpoints. Note that this option representation includes
 * support for multi-leg options.
 * <p>
 * For further domain knowledge on options, see the
 * <a href="https://www.investopedia.com/terms/o/option.asp">OptionList
 * Investopedia article</a>.
 *
 * @author <a href="https://github.com/albanoj2">Justin Albano</a>
 *
 * @since 0.8.2
 */
public class Option implements ApiElement {

	private String id;
	private String average_open_price;
	private String chain;
	private String created_at;
	private String direction;
	private String intraday_average_open_price;
	private String intraday_direction;
	private String intraday_quantity;
	private List<Leg> legs;
	private String quantity;
	private String strategy;
	private String symbol;
	private String trade_value_multiplier;
	private String updated_at;

	@Override
	public boolean requiresAuth() {
		return true;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getAverageOpenPrice() {
		return new BigDecimal(average_open_price);
	}

	public String getChainUrl() {
		return chain;
	}

	public ZonedDateTime getCreatedAt() {
		return ChronoFormatter.parseDefault(created_at);
	}

	public String getDirection() {
		return direction;
	}

	public BigDecimal getIntradayAverageOpenPrice() {
		return new BigDecimal(intraday_average_open_price);
	}

	public String getIntradayDirection() {
		return intraday_direction;
	}

	public BigDecimal getIntradayQuantity() {
		return new BigDecimal(intraday_quantity);
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public BigDecimal getQuantity() {
		return new BigDecimal(quantity);
	}

	public String getStrategy() {
		return strategy;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getTradeValueMultiplier() {
		return new BigDecimal(trade_value_multiplier);
	}

	public ZonedDateTime getUpdatedAt() {
		return ZonedDateTime.parse(updated_at);
	}

	void setId(String id) {
		this.id = id;
	}

	void setAverageOpenPrice(String average_open_price) {
		this.average_open_price = average_open_price;
	}

	void setChain(String chain) {
		this.chain = chain;
	}

	void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	void setDirection(String direction) {
		this.direction = direction;
	}

	void setIntradayAverageOpenPrice(String intraday_average_open_price) {
		this.intraday_average_open_price = intraday_average_open_price;
	}

	void setIntradayDirection(String intraday_direction) {
		this.intraday_direction = intraday_direction;
	}

	void setIntradayQuantity(String intraday_quantity) {
		this.intraday_quantity = intraday_quantity;
	}

	void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	void setTradeValueMultiplier(String trade_value_multiplier) {
		this.trade_value_multiplier = trade_value_multiplier;
	}

	void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isSimpleOption() {
		return legs != null && legs.size() == 1;
	}

    /**
     * Convert this {@link Option} to a {@link SimpleOption}
     * @return a SimpleOption copied from this Option
     * @throws NonSimpleOptionException If the option cannot be converted
     */
	public SimpleOption asSimpleOption() throws NonSimpleOptionException {

		if (isSimpleOption()) {
			Leg firstLeg = legs.get(0);
			return new SimpleOption(
				symbol,
				firstLeg.getOptionType(),
				getQuantity(),
				getAverageOpenPrice(),
				firstLeg.getStrikePrice(),
				firstLeg.getExpirationDate(),
				getTradeValueMultiplier()
			);
		}
		else {
			throw new NonSimpleOptionException();
		}
	}
}
