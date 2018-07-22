package com.ampro.robinhood.endpoint.fundamentals.data;

import com.ampro.robinhood.endpoint.ApiElementList;

public class TickerFundamentalList extends ApiElementList<TickerFundamental> {
    @Override
    public boolean requiresAuth() { return false; }
}
