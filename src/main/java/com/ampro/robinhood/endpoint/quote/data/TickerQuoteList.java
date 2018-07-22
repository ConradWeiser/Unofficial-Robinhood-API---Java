package com.ampro.robinhood.endpoint.quote.data;


import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.ApiElementList;
import com.ampro.robinhood.endpoint.quote.methods.GetTickerQuoteList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A list of {@link TickerQuote TickerQuoteElements} that Robinhood
 * returns from {@link GetTickerQuoteList GetTickerQuoteList}. <br>
 * This ElementList can contain a a maximum of 1,630 quotes and is
 * Semi-Paginated (meaning there is no next/previous like other ElemenetLists)
 * which is why it an {@link ApiElement} and not an {@link ApiElementList}
 *
 * @author Jonathan Augustine
 */
public class TickerQuoteList implements ApiElement, Iterable<TickerQuote> {

    @SerializedName("results")
    @Expose
    private List<TickerQuote> results;

    /** @return An unmodifiable non-null list of {@link TickerQuote quotes} */
    public List<TickerQuote> getQuotes() {
        results = results == null ? new ArrayList<>() : results;
        return Collections.unmodifiableList(results);
    }

    @Override
    public boolean requiresAuth() { return false; }

    @Override
    public Iterator<TickerQuote> iterator() {
        return this.results.iterator();
    }

}
