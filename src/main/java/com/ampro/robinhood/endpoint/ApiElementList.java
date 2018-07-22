package com.ampro.robinhood.endpoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class ApiElementList<E extends ApiElement>
        implements ApiElement {

    @SerializedName("previous")
    @Expose
    protected String previous;

    @SerializedName("next")
    @Expose
    protected String next;

    @SerializedName("results")
    @Expose
    protected List<E> results;

    /**
     * @return The list of results. If the list is paginated it may not
     *          contain all the results.
     */
    public List<E> getResults() { return this.results; }
    /** @return The URL returned to get the next paginated list */
    public String getNext() { return this.next; }
    /** @return The URL returned to get the previous paginated list */
    public String getPrevious() { return this.previous; }

    public boolean isEmpty() { return this.results.isEmpty(); }

}
