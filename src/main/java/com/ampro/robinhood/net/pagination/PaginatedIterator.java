package com.ampro.robinhood.net.pagination;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.ApiElementList;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PaginatedIterator<T extends ApiElement> implements Iterator<T> {

    private final Configuration config;

    private ApiElementList<?> apiElementList;

    private List<T> currentList;

    private int currentIndex;

    public PaginatedIterator(ApiElementList<T> start) {
        this.apiElementList = start;
        this.currentList = start.getResults();
        this.config = Configuration.getDefault();
        this.currentIndex = 0;
    }

    public PaginatedIterator(ApiElementList<T> start, Configuration config) {
        this.apiElementList = start;
        this.currentList = start.getResults();
        this.config = config;
        this.currentIndex = 0;
    }

    @Override
    public T next() {
        //We should always be checking if there is a next page before this
        //method is called, so there is no reason to check HasNextPage here
        // (when we load the next page it will throw an exception if there
        // is not another page anyway)
        //If the current List has been exhausted, load the next one
        if (currentIndex >= currentList.size()) {
            //This loads the next list, replaces the current ones, & resets
            // the current index
            loadNextList();
        }
        return currentList.get(currentIndex++);
    }

    /**
     * @return {@code true} if the {{@link #apiElementList}}
     *          has a {@link ApiElementList#getNext()} or the
     *          {{@link #currentList}} has not been exhausted
     */
    @Override
    public boolean hasNext() {
        return currentIndex < currentList.size()
                || apiElementList.getNext() != null;
    }

    /** Loads the next page in the paginated list & REPLACES THE CURRENT LIST */
    private void loadNextList() {
        if (apiElementList.getNext() == null)
            throw new NoSuchElementException();
        GetNextPage method = this.apiElementList.requiresAuth()
                ? new GetNextPage(this.apiElementList, config)
                : new GetNextPage(this.apiElementList);
        ApiElementList<T> newElementList = method.execute();
        this.apiElementList = newElementList;
        this.currentList = newElementList.getResults();
        this.currentIndex = 0;
    }

}
