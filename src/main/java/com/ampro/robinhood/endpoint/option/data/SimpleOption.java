package com.ampro.robinhood.endpoint.option.data;

import com.ampro.robinhood.endpoint.ApiElement;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TODO DOCS
 */
public class SimpleOption implements ApiElement {

	private final String symbol;
	private final String type;
	private final BigDecimal quantity;
	private final BigDecimal averageOpenPrice;
	private final BigDecimal strikePrice;
	private final LocalDate expirationDate;
	private final BigDecimal tradeMultiplier;

	public SimpleOption(String symbol, String type, BigDecimal quantity,
	                    BigDecimal averageOpenPrice, BigDecimal strikePrice,
	                    LocalDate expirationDate, BigDecimal tradeMultiplier) {
		this.symbol = symbol;
		this.type = type;
		this.quantity = quantity;
		this.averageOpenPrice = averageOpenPrice;
		this.strikePrice = strikePrice;
		this.expirationDate = expirationDate;
		this.tradeMultiplier = tradeMultiplier;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getAverageOpenPrice() {
		return averageOpenPrice;
	}

	public BigDecimal getStrikePrice() {
		return strikePrice;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public BigDecimal getTradeMultiplier() {
		return tradeMultiplier;
	}

	@Override
	public String toString() {
		return MessageFormat.format("{0} {1} calls of {2} with a strike price '$'{3} purchased for an average price of '$'{4} ({5} underlying shares)",
			getQuantity(),
			getExpirationDate().format(DateTimeFormatter.ofPattern("MMM dd, YYYY")),
			getSymbol(),
			getStrikePrice(),
			getAverageOpenPrice(),
			getTradeMultiplier()
		);
	}

	@Override
	public boolean requiresAuth() {
		return true;
	}
}
