package com.ampro.robinhood.endpoint.orders.data;

import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * A paginated list of {@link SecurityOrder Order elements}.
 * @author Jonathan Augustine
 */
public class SecurityOrderList extends ApiElementList<SecurityOrder> {
    @Override
    public boolean requiresAuth() { return true; }
}
