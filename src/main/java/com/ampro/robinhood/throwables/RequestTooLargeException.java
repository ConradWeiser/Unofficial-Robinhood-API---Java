package com.ampro.robinhood.throwables;

/**
 * An exception for API requests that are too large (ala get w/ 10000 items).
 * @author Jonathan Augustine
 */
public class RequestTooLargeException extends RobinhoodApiException {
    
	private static final long serialVersionUID = -6815804388399433737L;

	public RequestTooLargeException() {
        super();
    }

    public RequestTooLargeException(String message) {
        super(message);
    }
}
