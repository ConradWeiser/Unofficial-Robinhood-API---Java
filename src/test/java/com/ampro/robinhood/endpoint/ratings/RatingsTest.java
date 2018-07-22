package com.ampro.robinhood.endpoint.ratings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.ampro.robinhood.endpoint.ratings.data.RatingList;
import org.junit.Assert;
import org.junit.Test;

import com.ampro.robinhood.BaseTest;
import com.ampro.robinhood.endpoint.quote.data.TickerQuote;
import com.ampro.robinhood.throwables.RobinhoodApiException;

public class RatingsTest extends BaseTest {

	@Test
	public void getRatingsByTicker() throws RobinhoodApiException {
		RatingList ratings = api.getRatingsByTickers(tenTickers.get(0));
		assertEquals(1, ratings.getResults().size());
		ratings.getResults().forEach(Assert::assertNotNull);
	}

	@Test
	public void getRatingsByTickers() throws RobinhoodApiException {
		RatingList ratings = api.getRatingsByTickers(tenTickers.toArray(new String[10]));
		assertEquals(10, ratings.getResults().size());
		ratings.getResults().forEach(Assert::assertNotNull);
	}

	@Test
	public void getRatingsByInstrument() throws RobinhoodApiException {
		TickerQuote quote = api.getQuoteByTicker(tenTickers.get(0));
		RatingList ratings = api.getRatingsByInstrumentIds(quote.getInstrumentId());
		assertEquals(1, ratings.getResults().size());
		ratings.getResults().forEach(Assert::assertNotNull);
	}

	@Test
	public void getRatingsByInstruments() throws RobinhoodApiException {
		List<TickerQuote> quoteList = api.getQuoteListByTickers(tenTickers);
		List<String> instrumentIds = new ArrayList<>();
		for (TickerQuote quoteElement : quoteList) {
			instrumentIds.add(quoteElement.getInstrumentId());
		}
		RatingList ratings
				= api.getRatingsByInstrumentIds(instrumentIds.toArray(new String[10]));
		assertEquals(10, ratings.getResults().size());
		ratings.getResults().forEach(Assert::assertNotNull);
	}

}
