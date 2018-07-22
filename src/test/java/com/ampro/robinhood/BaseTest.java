package com.ampro.robinhood;

import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.net.request.RequestManager;
import com.ampro.robinhood.throwables.RobinhoodApiException;
import org.junit.Before;
import org.junit.Test;
import com.ampro.robinhood.endpoint.instrument.InstrumentTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTest {
    protected static RobinhoodApi api;
    protected static RequestManager requestManager;
    protected static List<String> tenTickers;
    protected static String MSFT;
    protected static String MSFT_URL;
    protected static String FAKE;
    protected static String KEYWORD;
    protected static List<Instrument> allInstruments;

    /**
     * Load all the instruments.
     * Relies on {@link InstrumentTest#getAllInstruments()}
     * @return The singleton list of all Robinhood instruments
     */
    protected List<Instrument> preLoadAllInstruments() {
        if (allInstruments == null) {
            allInstruments = api.getAllInstruments();
        }
        return allInstruments;
    }

    @Before
    public void init() throws IOException, RobinhoodApiException {
        api = new RobinhoodApi();
        requestManager = RequestManager.getInstance();
        tenTickers = new ArrayList<>(Arrays.asList(
                "MSFT", "VT", "VTI", "BAC", "DIS",
                "FB", "TSLA", "AAPL", "INTC", "BABA"
        ));
        MSFT = "MSFT";
        MSFT_URL = "https://api.robinhood" +
                ".com/instruments/50810c35-d215-4866-9758-0ada4ac79ffa/";
        FAKE = "KOKOBOKO";
        KEYWORD = "INC";
    }

    @Test
    public void test() {

    }



}
