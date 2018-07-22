package com.ampro.robinhood.endpoint.instrument.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * An Instrument contains data on any of the 10,000+ instruments tracked by
 * Robinhood's partners (~8,000 are trade-able)
 *
 * @author Jonathan Augustine
 */
public class Instrument implements ApiElement {

    @SerializedName("tradable_chain_id")
    @Expose
    private String tradableChainId;

    @SerializedName("min_tick_size")
    @Expose
    private Object minTickSize;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("splits")
    @Expose
    private String splits;

    @SerializedName("margin_initial_ratio")
    @Expose
    private String marginInitialRatio;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("quote")
    @Expose
    private String quote;

    /** Seemingly a duplicate variable for {@link Instrument#tradeable}*/
    @SerializedName("tradability")
    @Expose
    private String tradability;

    @SerializedName("bloomberg_unique")
    @Expose
    private String bloombergUnique;

    @SerializedName("list_date")
    @Expose
    private String listDate;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("fundamentals")
    @Expose
    private String fundamentals;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("day_trade_ratio")
    @Expose
    private String dayTradeRatio;

    @SerializedName("tradeable")
    @Expose
    private Boolean tradeable;

    @SerializedName("maintenance_ratio")
    @Expose
    private String maintenanceRatio;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("market")
    @Expose
    private String market;

    @SerializedName("simple_name")
    @Expose
    private String simpleName;

    @Override
    public boolean requiresAuth() { return false; }

    public String getTradableChainId() {
        return tradableChainId;
    }

    public Object getMinTickSize() {
        return minTickSize;
    }

    public String getType() {
        return type;
    }

    public String getSplits() {
        return splits;
    }

    public String getMarginInitialRatio() {
        return marginInitialRatio;
    }

    public String getUrl() {
        return url;
    }

    public String getQuote() {
        return quote;
    }

    public String getTradability() {
        return tradability;
    }

    public String getBloombergUnique() {
        return bloombergUnique;
    }

    public String getListDate() {
        return listDate;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFundamentals() {
        return fundamentals;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getDayTradeRatio() {
        return dayTradeRatio;
    }

    public Boolean getTradeable() {
        return tradeable;
    }

    public String getMaintenanceRatio() {
        return maintenanceRatio;
    }

    public String getId() {
        return id;
    }

    public String getMarket() {
        return market;
    }

    public String getSimpleName() {
        return simpleName;
    }

}
