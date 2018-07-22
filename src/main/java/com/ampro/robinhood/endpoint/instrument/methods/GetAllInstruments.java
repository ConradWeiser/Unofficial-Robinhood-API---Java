package com.ampro.robinhood.endpoint.instrument.methods;

import com.ampro.robinhood.endpoint.instrument.data.InstrumentList;

/**
 * Get all instruments listed by the Robinhood API
 * The result is Paginated
 * @author Jonathan Augustine
 */
public class GetAllInstruments extends GetInstrument {

    private static final GetAllInstruments defaultMethod = new GetAllInstruments();

    /**
     * Since this API method cannot be modified, just return a static one
     * @return the default {@link GetAllInstruments} object
     */
    public static synchronized GetAllInstruments getDefault() {
        return defaultMethod;
    }

    /**
     * Get all instruments listed by the Robinhood API
     * The result is Paginated.
     * @author Jonathan Augustine
     */
    private GetAllInstruments() {
        super();
        this.setUrlBase("https://api.robinhood.com/instruments/");
        this.setReturnType(InstrumentList.class);
    }

    public static InstrumentList get() {
        return defaultMethod.execute();
    }

}
