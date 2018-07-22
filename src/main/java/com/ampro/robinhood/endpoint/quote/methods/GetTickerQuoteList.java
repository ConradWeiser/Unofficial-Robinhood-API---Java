package com.ampro.robinhood.endpoint.quote.methods;


import com.ampro.robinhood.endpoint.quote.data.TickerQuoteList;
import com.ampro.robinhood.net.request.RequestMethod;
import com.ampro.robinhood.throwables.RequestTooLargeException;

import java.util.Collection;

/**
 * (from <a href="https://github.com/sanko/Robinhood/blob/master/Quote.md#gather-quote-data-for-multiple-ticker-symbols-in-a-single-api-call">
     Unofficial Robinhood API
      </a>)<br>
 * "Gather quote data for a list of symbols at once...Returned data is
 * semi-paginated (in that there are no next or previous keys)." <br>
 *     Returns a {@link TickerQuoteList}.
 *
 * @author Jonathan Augustine
 */
public class GetTickerQuoteList extends GetQuote {

    /**
     * Construct a method to get multiple security quotes by tickers
     * (e.g. MSFT, FIT, etc). <br>
     *     Any tickers that do not exist (at least not on Robinhood) will
     *     be returned as null in the element list.
     *
     * @param tickers The tickers to request quotes of.
     * @throws RequestTooLargeException if the parameter is larger than 1,630
     */
    public GetTickerQuoteList(Collection<String> tickers)
    throws RequestTooLargeException {
        super();
        if (tickers.size() > MAX_TICKERS) {
            throw new RequestTooLargeException(
                    "Ticker request must be under " + MAX_TICKERS
            );
        }
        this.setUrlBase(RH_URL + "/quotes/");
        //Reform the collection as a url param
        //replaceAll("[\\[\\]\\s+]", "") replaces "[]" and empty spaces
        // in collection.toString
        addQueryParameter("symbols", tickers.toString().replaceAll("[\\[\\]\\s+]", ""));

        //This method is ran as GET
        this.setMethodType(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(TickerQuoteList.class);

    }

}
