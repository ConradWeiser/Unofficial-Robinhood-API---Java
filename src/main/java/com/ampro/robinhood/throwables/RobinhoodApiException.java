package com.ampro.robinhood.throwables;

@SuppressWarnings("serial")
public class RobinhoodApiException extends Exception {

	public RobinhoodApiException() {
	    super();
	}

	public RobinhoodApiException(String message) {
		super(message);
	}

	public RobinhoodApiException(Throwable e) {
		super(e);
	}

}
