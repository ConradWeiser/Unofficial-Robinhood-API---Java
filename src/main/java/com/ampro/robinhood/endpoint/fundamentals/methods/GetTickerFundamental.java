package com.ampro.robinhood.endpoint.fundamentals.methods;

import com.ampro.robinhood.endpoint.fundamentals.data.TickerFundamental;
import com.ampro.robinhood.net.request.RequestMethod;

public class GetTickerFundamental extends GetFundamental {

	public GetTickerFundamental(String ticker) {
		super();

		this.setUrlBase("https://api.robinhood.com/fundamentals/{ticker}/");

		this.addRouteParameter("ticker", ticker);

		//This method is ran as GET
		this.setMethodType(RequestMethod.GET);

		//Declare what the response should look like
		this.setReturnType(TickerFundamental.class);
	}

}
