package com.ampro.robinhood;

import com.ampro.robinhood.endpoint.authorize.data.AuthorizationData;
import com.ampro.robinhood.throwables.NotLoggedInException;

/**
 * The Configuration stores authorization information about an instance of the
 * {@link RobinhoodApi}.
 * <p>
 * Many things (mostly {@link com.ampro.robinhood.net.ApiMethod ApiMethods})
 * require a Configuration to function
 *
 * @author Jonathan Augustine
 */
public class Configuration {

	/** How long should the system wait between requests? (milisec)*/
	private static long rateLimit = 2000;

	/** The default Config (to reduce repeated allocations for non-auth methods) */
	private static Configuration defaultConfig;

	/** Information about login & authorization, contains the account auth token */
	private AuthorizationData authorizationData;

	/**
	 * The Account Number for the account logged in. This variable is used for
     * various other functions.
	 */
	private String accountNumber;

	public Configuration() {
		this.authorizationData = new AuthorizationData();
		this.accountNumber = null;
	}

	public AuthorizationData getAuthData() {
	    return this.authorizationData;
    }

    public void setAuthData(AuthorizationData authData) {
	    this.authorizationData = authData;
    }

	/**
	 * Whether or not an authToken exists.
	 * If an authToken does not exist than there is no current logged in user.
	 * @return {@code true} if an authToken exists
	 */
	public boolean hasToken() {
		return this.authorizationData.getToken() != null;
	}

	/**
	 * Method which gets the saved authorization token if the user is logged in.
	 * If one does not exist, it throws an error reminding the user to run the
     * login functions
	 * first.
	 *
	 * @return the saved Token for the logged in user
	 * @throws NotLoggedInException if there is no stored Token. This must be
     *                                  populated by the setToken() method first
	 */
	public String getToken() {
		if(this.authorizationData.getToken() == null) {
            throw new NotLoggedInException();
        }
		return this.authorizationData.getToken();
	}

	/**
	 * Method which registers the authToken for the user into the Configuration Manager
	 *
	 * @param token verified Authorization Token for the user
	 */
	public void setAuthToken(String token) {
		this.authorizationData.setToken(token);
	}

	/**
	 * @return The account number
	 * @throws NotLoggedInException If the {@link Configuration} is
     * not logged in
	 */
	public String getAccountNumber() {
		if (this.accountNumber == null) {
            throw new NotLoggedInException();
        }
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Method returning the Account URL for the logged in user. This is created
     * by appending the account number to a base URL. This is valid in most
     * order requests.
	 * @return the account URL
     * @throws NotLoggedInException If the {@link Configuration} is not logged in
	 */
	public String getAccountUrl() {
		if (this.accountNumber == null) {
            throw new NotLoggedInException();
        }
		return "https://api.robinhood.com/accounts/" + this.accountNumber + "/";
	}

	/** @return The default Configuration (i.e. with no account data) */
	public static Configuration getDefault() {
	    if (defaultConfig == null) {
	        defaultConfig = new Configuration();
        }
		return defaultConfig;
	}

	/**
	 * @return the current ratelimit. By default, this is 500 milliseconds
     * (.5 seconds)
	 */
	public static long getRatelimit() {
		return Configuration.rateLimit;
	}

	/**
	 * Set a new ratelimit (in milliseconds)
     * @param newRateLimitValue The new RateLimit in milliseconds
	 */
	public static void setRatelimit(int newRateLimitValue) {
		Configuration.rateLimit = newRateLimitValue;
	}

    /** Clears all user data from the {@link Configuration}. */
    public void clear() {
        this.authorizationData = null;
        this.accountNumber = null;
    }
}
