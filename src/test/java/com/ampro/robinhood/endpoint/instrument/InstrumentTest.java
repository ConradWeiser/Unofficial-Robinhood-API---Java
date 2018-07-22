package com.ampro.robinhood.endpoint.instrument;

import com.ampro.robinhood.BaseTest;
import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.endpoint.instrument.methods.GetInstrumentByUrl;
import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.throwables.RobinhoodApiException;
import com.ampro.robinhood.throwables.TickerNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class InstrumentTest extends BaseTest {

    @Test
    public void getInstrumentByTicker() throws RobinhoodApiException {
        Instrument instrument = api.getInstrumentByTicker(MSFT);
        assertNotNull(instrument);
    }

    @Test(expected = TickerNotFoundException.class)
    public void getFakeTickerInstrument() throws RobinhoodApiException {
        api.getInstrumentByTicker(FAKE);
    }

    @Test
    public void getInstrumentByUrl() {
        ApiMethod method = new GetInstrumentByUrl(MSFT_URL);
        Instrument instrument = requestManager.makeApiRequest(method);
        assertNotNull(instrument);
    }

    @Test
    public void getAllInstruments() {
        allInstruments = api.getAllInstruments();
        allInstruments.forEach(Assert::assertNotNull);
    }

    @Test
    public void searchInstrumentByKeyword() {
        api.getInstrumentsByKeyword(KEYWORD);
    }

}
