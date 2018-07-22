package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * This element should not exist for very long hopefully as Robinhood updates
 * their API. Currently the getAccounts command returns the data inside of an
 * array after a "next" and "previous" field which are never filled out. This
 * class should never be accessed directly, as it merely is filtered out
 * pulling the only account from the results. Gson needs this in order to
 * work, though.
 */
public class AccountArrayWrapper extends ApiElementList<Account> {

	/**
	 * Currently, there will NEVER be more than just one of these.
     * To make it easy for the user to use the methods requiring
	 * this, we only return the existing data.
     * @since 0.6
	 * @return The {@link Account} within the result.
	 */
	public Account getResult() {
		return results.get(0);
	}

    /**
     * Does nothing
     * @return Nothing
     */
    @Override
    @Deprecated
    public String getNext() {
        return next;
    }
    /**
     * Does nothing
     * @return Nothing
     */
    @Override
    @Deprecated
    public String getPrevious() {
        return previous;
    }

    /**
     * @return If the element requires an authorized API (i.e. Is user-specific
     * data)
     */
    @Override
    public boolean requiresAuth() {
        return true;
    }
}
