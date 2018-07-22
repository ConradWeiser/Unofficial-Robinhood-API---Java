package com.ampro.robinhood.endpoint.instrument.methods;

import com.ampro.robinhood.endpoint.instrument.data.InstrumentList;

public class SearchInstrumentsByKeyword extends GetInstrument {
    public SearchInstrumentsByKeyword(String keyword) {
        super();
        setUrlBase("https://api.robinhood.com/instruments/");
        addQueryParameter("query", keyword);
        setReturnType(InstrumentList.class);
    }
}
