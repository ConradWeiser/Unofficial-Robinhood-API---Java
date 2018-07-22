package com.ampro.robinhood.endpoint.account.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.account.data.PositionList;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * @author SirensBell, Jonathan Augustine
 */
public class GetAccountPositions extends Account {

    public GetAccountPositions(Configuration config) {
        super(config);

        this.setUrlBase(RH_URL + "/accounts/{accountId}/positions/");
        //Get the current account ID to be used with the position search
        this.addRouteParameter("accountId", config.getAccountNumber());

        //This method is to be ran as GETe
        this.setMethodType(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(PositionList.class);

    }
}
