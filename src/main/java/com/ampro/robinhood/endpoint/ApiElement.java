package com.ampro.robinhood.endpoint;

/**
 * A basis class for elements received from the Robinhood API
 * @author Jonathan Augustine
 */
public interface ApiElement {
    /**
     * @return If the element requires an authorized API
     *              (i.e. Is user-specific data)
     */
    default boolean requiresAuth() {
        return false;
    }
}
