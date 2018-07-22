package com.ampro.robinhood.endpoint.orders.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.orders.data.SecurityOrderList;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * REST GET method to retrieve a user's orders.
 * @author Jonathan Augustine
 */
public class GetOrdersMethod extends OrderMethod {

    /**
     * Set api URL to GET from and return type
     * ({@link SecurityOrderList}).
     * @param config a logged in {@link Configuration}
     */
    public GetOrdersMethod(Configuration config) {
        super(config);
        this.setUrlBase(RH_URL + "/orders/");

        this.addHeaderParameter("Content-Type", "application/x-www-form-urlencoded");

        //This method should be ran as POST
        this.setMethodType(RequestMethod.GET);

        this.setReturnType(SecurityOrderList.class);
    }

    /** Does nothing */
    @Override
    protected void setOrderParameters() {}
}
