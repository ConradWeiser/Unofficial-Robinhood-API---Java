package com.ampro.robinhood.endpoint.account.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.account.data.AccountHolderInvestmentProfile;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * Created by SirensBell on 5/23/2017.
 */
public class GetAccountInvestmentInformation extends Account {

    public GetAccountInvestmentInformation(Configuration config) {
        super(config);

        this.setUrlBase(RH_URL + "/user/investment_profile");

        //This method is to be ran as GET
        this.setMethodType(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(AccountHolderInvestmentProfile.class);

    }
}
