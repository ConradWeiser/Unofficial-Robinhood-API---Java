package com.ampro.robinhood.endpoint.collections;

import com.ampro.robinhood.BaseTest;
import com.ampro.robinhood.endpoint.collection.data.InstrumentCollectionList;
import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.throwables.RobinhoodApiException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InstrumentCollectionsTest extends BaseTest {

    @Test
    public void getTop100() throws RobinhoodApiException {
        InstrumentCollectionList collectionList
                = api.getCollectionData("100-most-popular");
        List<Instrument> instruments = collectionList.getInstruments();
        Assert.assertEquals(100, instruments.size());
        instruments.forEach(Assert::assertNotNull);
    }

    @Test
    public void getManufacturing() throws RobinhoodApiException {
        InstrumentCollectionList collectionList
                = api.getCollectionData("manufacturing");
        List<Instrument> instruments = collectionList.getInstruments();
        instruments.forEach(Assert::assertNotNull);
    }

}
