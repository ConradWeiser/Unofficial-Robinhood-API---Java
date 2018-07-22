package com.ampro.robinhood.endpoint.collection.methods;

import com.ampro.robinhood.endpoint.collection.data.InstrumentCollectionList;
import com.ampro.robinhood.endpoint.fundamentals.methods.GetFundamental;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * The Class GetCollectionData that allows querying for stocks based on
 * collection name.
 *
 * @author MainStringArgs
 */
public class GetCollectionData extends GetFundamental {

	/**
	 * Instantiates the GetCollectionData class. Examples of collections include
	 * 'manufacturing', 'consumer-product', and '100-most-popular'
	 *
	 * @param collectionName
	 *            the collection name to query for.
	 */
	public GetCollectionData(final String collectionName) {
		super();

		this.setUrlBase(RH_URL + "/midlands/tags/tag/{collectionName}/");
		this.addRouteParameter("collectionName", collectionName);

		// This method is ran as GET
		this.setMethodType(RequestMethod.GET);

		// Declare what the response should look like
		this.setReturnType(InstrumentCollectionList.class);
	}
}
