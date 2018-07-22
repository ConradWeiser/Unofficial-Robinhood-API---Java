package com.ampro.robinhood.endpoint.account.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.account.data.AccountArrayWrapper;
import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * An {@link ApiMethod} used to get a list of a user's
 * accounts.
 * !!NOTE~~ As of 3/7/2018, this will only ever return a single account, so we
 * will see what the Robinhood team does there...maybe
 * @author Conrad Weisse
 */
public class GetAccounts extends Account {

	/**
	 * Get a list of a user's accounts. <br>
	 * <b>(ONLY CONTAINS 1 ACCOUNT ELEMENT AS OF 3/7/2018)</b>
	 * @param config The {@link Configuration}
	 */
	public GetAccounts(Configuration config) {
	    super(config);

		this.setUrlBase(RH_URL + "/accounts/");

		//This method is ran as GET
		this.setMethodType(RequestMethod.GET);

		//Declare what the response should look like
		this.setReturnType(AccountArrayWrapper.class);
	}

}
