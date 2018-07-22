package com.ampro.robinhood.throwables;

/**
 * Thrown when an unauthorized instance tries to get account data
 * @author Jonathan Augustine
 */
@SuppressWarnings("serial")
public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String message) {
        super(message);
    }

    /**
     * Override the default method to get more information about why the error
     * is thrown
     * @return more information about the exception
     */
    @Override
    public String getMessage() {
        return super.getMessage() == null
                ? "There is no Authorization token available."
                : super.getMessage();
    }

}
