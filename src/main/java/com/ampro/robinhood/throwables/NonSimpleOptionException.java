package com.ampro.robinhood.throwables;

public class NonSimpleOptionException extends RobinhoodApiException {

	private static final long serialVersionUID = 8285526229611506289L;

	public NonSimpleOptionException() {
		super("Option with multiple legs cannot be converted to simple option");
	}

}
