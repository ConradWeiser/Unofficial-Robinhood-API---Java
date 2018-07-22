package com.ampro.robinhood.endpoint.instrument.methods;

import com.ampro.robinhood.endpoint.instrument.data.Instrument;

/**
 * Get an {@link Instrument} by a predefined URL
 * @author Jonathan Augustine
 */
public class GetInstrumentByUrl extends GetInstrument {
    public GetInstrumentByUrl(String url) {
        super();
        this.setUrlBase(url);
        this.setReturnType(Instrument.class);
    }
}
