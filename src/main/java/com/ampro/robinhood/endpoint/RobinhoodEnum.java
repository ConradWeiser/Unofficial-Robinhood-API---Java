package com.ampro.robinhood.endpoint;

/**
 * A basis for all set values returned by the Robinhood API.
 * Anything that the API can only return a narrow, defined set of can be
 * made into an {@link Enum} implementing this interface and replicating
 * {@link com.ampro.robinhood.endpoint.orders.enums.OrderTrigger this} pattern.
 *
 * @author Jonathan Augustine
 * @since 0.8.3
 */
public interface RobinhoodEnum {

    /**
     * Get the Robinhood API's string that corresponds to this enum
     * @return The enum as a Robinhood JSON formatted string.
     */
    String getValue();

}
