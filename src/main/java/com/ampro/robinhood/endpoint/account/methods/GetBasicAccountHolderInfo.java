package com.ampro.robinhood.endpoint.account.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.account.data.BasicAccountHolderInfo;
import com.ampro.robinhood.net.request.RequestMethod;

public class GetBasicAccountHolderInfo extends Account {

	public GetBasicAccountHolderInfo(Configuration config) {
		super(config);

		this.setUrlBase(RH_URL + "/user/basic_info/");

		//This method is to be ran as GET
		this.setMethodType(RequestMethod.GET);

		//Declare what the response should look like
		this.setReturnType(BasicAccountHolderInfo.class);
	}

}
