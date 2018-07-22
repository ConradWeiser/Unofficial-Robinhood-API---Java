package com.ampro.robinhood.endpoint.orders.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.orders.data.SecurityOrder;
import com.ampro.robinhood.net.request.RequestMethod;
import com.ampro.robinhood.throwables.RobinhoodApiException;

/**
 * An API method to cancel an open order.
 *
 * @author Jonathan Augustine
 */
public class CancelOrderMethod extends OrderMethod {

    /**
     * Build a cancel order method.
     * @param order The order to cancel
     * @param config A logged in {@link Configuration}
     * @throws RobinhoodApiException If the order cannot be cancelled
     */
    public CancelOrderMethod(SecurityOrder order, Configuration config)
    throws RobinhoodApiException {
        super(config);
        switch (order.getTransactionState()) {
            case UNCONFIRMED:
            case CONFIRMED:
            case PARTIALLY_FILLED:
            case FILLED:
            case REJECTED:
            case CANCELED:
            case FAILED:
                throw new RobinhoodApiException(
                        "Order " + order.getTransactionState().getValue());
            default:
                break;
        }
        if (order.getCancel() == null) {
            throw new RobinhoodApiException("Order cancel URL is null.");
        }
        this.setMethodType(RequestMethod.POST);
        this.setUrlBase(order.getCancel().toExternalForm());
        this.setReturnType(SecurityOrder.class);
    }

    /** Does nothing */
    @Override
    protected void setOrderParameters() {}
}
