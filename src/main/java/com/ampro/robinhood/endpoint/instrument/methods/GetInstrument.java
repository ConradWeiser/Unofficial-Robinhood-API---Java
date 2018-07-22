package com.ampro.robinhood.endpoint.instrument.methods;

import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * An HTTPS wrapper object for making Instrument Get requests to the Robinhood api.
 *
 * @author Jonathan Augustine
 */
public abstract class GetInstrument extends ApiMethod {
    protected GetInstrument() {
        super(Configuration.getDefault());
        this.setMethodType(RequestMethod.GET);
    }
}
