package com.ampro.robinhood.endpoint.instrument.methods;

import com.ampro.robinhood.endpoint.instrument.data.InstrumentList;

/**
 * This returns a
 * {@link InstrumentList}
 * ... IDK why but it does, ask Robinhood idk
 * @author Jonathan Augustine
 */
public class GetInstrumentByTicker extends GetInstrument {
    public GetInstrumentByTicker(String ticker) {
        super();
        setUrlBase("https://api.robinhood.com/instruments/");
        addQueryParameter("symbol", ticker);
        //Declare what the response should look like
        this.setReturnType(InstrumentList.class);
    }
}
