package com.ampro.robinhood.endpoint.account.data;


import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.endpoint.instrument.methods.GetInstrumentByUrl;
import com.ampro.robinhood.net.request.RequestManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Element containing information of a given position which exists on a users
 * watchlist.
 *
 * @author Jonathan Augustine
 */
public class Position implements ApiElement {

    @SerializedName("account")
    @Expose
    public String accountUrl;

    @SerializedName("instrument")
    @Expose
    public String instrumentUrl;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @SerializedName("shares_held_for_stock_grants")
    @Expose
    public float sharesHeldForStockGrants;

    @SerializedName("intraday_quantity")
    @Expose
    public float intradayQuantity;

    @SerializedName("intraday_average_buy_price")
    @Expose
    public float intradayAverageBuyPrice;

    @SerializedName("shares_held_for_buys")
    @Expose
    public float sharesHeldForBuys;

    @SerializedName("average_buy_price")
    @Expose
    public float averageBuyPrice;

    @SerializedName("shares_held_for_sells")
    @Expose
    public float sharesHeldForSells;

    @SerializedName("quantity")
    @Expose
    public float quantity;

    @Override
    public boolean requiresAuth() { return true; }

    public Instrument getInstrumentElement() {
        return RequestManager.getInstance().makeApiRequest(
                new GetInstrumentByUrl(this.instrumentUrl)
        );
    }

    public float getSharesHeldForStockGrants() {
        return sharesHeldForStockGrants;
    }

    public String getAccount() {
        return accountUrl;
    }

    public float getIntradayQuantity() {
        return intradayQuantity;
    }

    public float getIntradayAverageBuyPrice() {
        return intradayAverageBuyPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public float getSharesHeldForBuys() {
        return sharesHeldForBuys;
    }

    public float getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public float getSharesHeldForSells() {
        return sharesHeldForSells;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getInstrumentUrl() { return this.instrumentUrl; }

}
