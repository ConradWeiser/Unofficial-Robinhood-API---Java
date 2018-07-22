package com.ampro.robinhood.endpoint.option.data;

import com.ampro.robinhood.endpoint.ApiElement;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Abstraction representing a leg for single Robinhood investment derivative.
 * <p>
 * For further domain knowledge on options, see the
 * <a href="https://www.investopedia.com/terms/l/leg.asp">Leg
 * Investopedia article</a>.
 *
 * @author <a href="https://github.com/albanoj2">Justin Albano</a>
 *
 * @since 0.8.2
 */
public class Leg implements ApiElement {

	private String id;
	private String expiration_date;
	private String option;
	private String option_type;
	private String position;
	private String position_type;
	private String ratio_quantity;
	private String strike_price;

	public String getId() {
		return id;
	}

	public LocalDate getExpirationDate() {
		return LocalDate.parse(expiration_date);
	}

	public String getOptionUrl() {
		return option;
	}

	public String getOptionType() {
		return option_type;
	}

	public String getPositionUrl() {
		return position;
	}

	public String getPositionType() {
		return position_type;
	}

	public BigDecimal getRatioQuantity() {
		return new BigDecimal(ratio_quantity);
	}

	public BigDecimal getStrikePrice() {
		return new BigDecimal(strike_price);
	}

    /**
     * ! Should only be used for test purposes !
     *
     * @param id The new ID
     */
	void setId(String id) {
		this.id = id;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param expiration_date the new expiration date
	 */
	public void setExpirationDate(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param option The new option
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param option_type the new option type
	 */
	public void setOptionType(String option_type) {
		this.option_type = option_type;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param position The new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param position_type the new position type
	 */
	public void setPositionType(String position_type) {
		this.position_type = position_type;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param ratio_quantity the new ratio quality
	 */
	public void setRatioQuantity(String ratio_quantity) {
		this.ratio_quantity = ratio_quantity;
	}

	/**
	 * ! Should only be used for test purposes !
     *
	 * @param strike_price the new strike price
	 */
	public void setStrikePrice(String strike_price) {
		this.strike_price = strike_price;
	}

	@Override
	public boolean requiresAuth() {
		return false;
	}
}
