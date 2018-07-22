package com.ampro.robinhood.throwables;

/**
 * An exception to be thrown when a ticker is not found after polling Robinhood
 * @author Jonathan Augustine
 */
public class TickerNotFoundException extends RobinhoodApiException {

	private static final long serialVersionUID = -4401894268654476314L;

	private final String ticker;

    public TickerNotFoundException() {
        super();
        this.ticker = "";
    }

    public TickerNotFoundException(String ticker) {
        super(ticker);
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    @Override
    public String getMessage() {
        return "The Stock Symbol " + ticker +" was not found.";
    }
}
