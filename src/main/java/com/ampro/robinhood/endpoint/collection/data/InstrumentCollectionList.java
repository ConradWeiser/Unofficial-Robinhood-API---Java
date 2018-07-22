package com.ampro.robinhood.endpoint.collection.data;

import java.util.ArrayList;
import java.util.List;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.endpoint.instrument.methods.GetInstrumentByUrl;
import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.net.request.RequestManager;
import com.ampro.robinhood.throwables.RobinhoodApiException;

/**
 * The Class InstrumentCollectionList.
 * Examples of collections include 'manufacturing', 'consumer-product', and
 * '100-most-popular'
 * @author MainStringArgs
 */
public class InstrumentCollectionList implements ApiElement {

	private String name;
    private String description;
    private String canonical_examples;
    private String slug;

	/**
	 * The list of instrument URLs that the request returned.
     * This cannot be marked transient because it is set by GSON.
	 */
	private List<String> instruments;

	/**
	 * Gets the instrument list. (Makes API Call)
	 *
	 * @return the instrument fundamental list
	 * @throws RobinhoodApiException
	 *             the robinhood api exception
	 */
	public List<Instrument> getInstruments()
	throws RobinhoodApiException {
        RequestManager rm = RequestManager.getInstance();

        if (instruments == null) {
            throw new RobinhoodApiException("No available Instruments.");
        }

        // Return the array as a list for ease-of-use
        List<Instrument> elementList = new ArrayList<>();

        instruments.forEach(url -> {
            ApiMethod method = new GetInstrumentByUrl(url);
            elementList.add(rm.makeApiRequest(method));
        });

        return elementList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCanonical_examples() {
        return canonical_examples;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public boolean requiresAuth() {
        return false;
    }
}
