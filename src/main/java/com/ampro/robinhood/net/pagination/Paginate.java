package com.ampro.robinhood.net.pagination;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.net.ApiMethod;

/**
 * An API method for pagination
 * @author Jonathan Augustine
 */
public class Paginate extends ApiMethod {

    /**
     * Build a Paginate method instance.
     *
     * @param type The return type
     * @param requiresAuth If the return type requires an authorization token
     * @param config A logged in {@link Configuration}
     * @throws com.ampro.robinhood.throwables.NotLoggedInException
     *              If the the element list requires a token and the instance
     *              is not logged in.
     */
    protected Paginate(Class<?> type, boolean requiresAuth, Configuration config) {
        super(config);
        setReturnType(type);
        if (requiresAuth) {
            this.addAuthTokenParameter();
        }
    }

}
