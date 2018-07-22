package com.ampro.robinhood.net.request;

import com.ampro.robinhood.endpoint.RobinhoodEnum;

public enum LoginStatus implements RobinhoodEnum {
	SUCCESS,
    /** Requires Multifactor Authorization */
    REQ_MFA,
	FAILURE,
	NOT_LOGGED_IN;

	private String value;
	LoginStatus() { this.value = null; }
	LoginStatus(String s) { value = s; }

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.value != null ? value : super.toString();
	}

    /**
     * Set the value (and toString) of this enum.
     *
     * @param s The new value
     * @return this
     */
	public LoginStatus setValue(String s) {
	    value = s;
	    return this;
    }
}
