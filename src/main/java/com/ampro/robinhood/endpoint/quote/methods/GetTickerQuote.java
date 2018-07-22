package com.ampro.robinhood.endpoint.quote.methods;

import com.ampro.robinhood.endpoint.quote.data.TickerQuote;

/**
 * Created by SirensBell on 6/19/2017.
 */
public class GetTickerQuote extends GetQuote {

    public GetTickerQuote(String ticker) {
        super();
        this.setUrlBase("https://api.robinhood.com/quotes/{ticker}/");
        addRouteParameter("ticker", ticker);
        //Declare what the response should look like
        this.setReturnType(TickerQuote.class);
    }
}
