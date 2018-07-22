package com.ampro.robinhood.endpoint.account.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.account.data.AccountHolderEmployment;
import com.ampro.robinhood.net.request.RequestMethod;

public class GetAccountHolderEmploymentInfo extends Account {

	public GetAccountHolderEmploymentInfo(Configuration config) {
		super(config);

		this.setUrlBase(RH_URL + "/user/employment/");

		//This method is ran as GET
		this.setMethodType(RequestMethod.GET);

		//Declare what the response should look like
		this.setReturnType(AccountHolderEmployment.class);
	}

}
