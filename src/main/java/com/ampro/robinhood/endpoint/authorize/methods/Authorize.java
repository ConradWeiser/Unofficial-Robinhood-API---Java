package com.ampro.robinhood.endpoint.authorize.methods;

import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.Configuration;

/**
 * A base class for Authorization (log in/out) ApiMethods.
 *
 * @author Jonathan Augustine
 */
public abstract class Authorize extends ApiMethod {

	protected Authorize(Configuration config) {
	    super(config);
    }

}
