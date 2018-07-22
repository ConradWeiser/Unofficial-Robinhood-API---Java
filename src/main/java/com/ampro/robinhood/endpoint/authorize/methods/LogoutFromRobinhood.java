package com.ampro.robinhood.endpoint.authorize.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * ApiMethod to log a {@link com.ampro.robinhood.RobinhoodApi} instance out.
 * Has a {@link Void} return type.
 *
 * @author Conrad Weise
 */
public class LogoutFromRobinhood extends Authorize {

	/**
	 * ApiMethod to log a {@link com.ampro.robinhood.RobinhoodApi} instance out.
	 * Has a {@link Void} return type.
	 *
	 * @param configuration A logged in {@link Configuration}
	 */
	public LogoutFromRobinhood(Configuration configuration) {
		super(configuration);
		this.setUrlBase(RH_URL + "/api-token-logout/");
		//This needs to be ran as POST
		this.setMethodType(RequestMethod.POST);
		//We are not expecting a response back.
		this.setReturnType(Void.TYPE);
	}

}
