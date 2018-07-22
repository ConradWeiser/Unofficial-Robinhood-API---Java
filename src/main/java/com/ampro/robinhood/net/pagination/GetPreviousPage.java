package com.ampro.robinhood.net.pagination;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * API REST call to get the previous "page" of a paginated list.
 * @author Jonathan Augustine
 */
public class GetPreviousPage extends Paginate {

    /**
     * Construct a {@link GetPreviousPage} with a {@link Configuration}
     * Use this constructor for {@link ApiElement ApiElements} that require
     * an authorization token.
     * @param elementList The {@link ApiElementList} to paginated through
     * @param config The {@link Configuration}
     * @param <E> The ApiElement type
     */
    public <E extends ApiElement> GetPreviousPage(ApiElementList<E> elementList,
                                                  Configuration config) {
        super(elementList.getClass(), elementList.requiresAuth(), config);
        setUrlBase(elementList.getNext());
    }

    /**
     * Construct a {@link GetPreviousPage} without a {@link Configuration}.
     * @param elementList The {@link ApiElementList} to paginated through
     * @param <E> The ApiElement type
     */
    public <E extends ApiElement> GetPreviousPage(ApiElementList<E> elementList) {
        this(elementList, Configuration.getDefault());
    }

}
