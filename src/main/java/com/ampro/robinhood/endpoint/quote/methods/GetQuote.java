package com.ampro.robinhood.endpoint.quote.methods;


import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * Wrapper for HTTP(S) wrapper for making get requests to get security quotes
 * @author Jonathan Augustine
 */
public class GetQuote extends ApiMethod {

    protected GetQuote() {
        super(Configuration.getDefault());
        //This method is ran as GET
        this.setMethodType(RequestMethod.GET);
    }
}
