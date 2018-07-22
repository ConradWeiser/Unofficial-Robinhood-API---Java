package com.ampro.robinhood.endpoint.instrument.data;

import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * A Paginated List of {@link Instrument}
 * @author Jonathan Augustine
 */
public class InstrumentList extends ApiElementList<Instrument> {
    @Override
    public boolean requiresAuth() { return false; }
}
