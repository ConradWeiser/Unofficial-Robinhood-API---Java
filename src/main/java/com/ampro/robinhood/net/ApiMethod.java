package com.ampro.robinhood.net;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.net.request.RequestManager;
import com.ampro.robinhood.net.request.RequestMethod;
import com.ampro.robinhood.throwables.NotLoggedInException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * A Wrapper object for making REST requests through
 * {@link io.github.openunirest.http.Unirest}
 * @author Conrad Weisse, Modified by Jonathan Augustine
 */
public abstract class ApiMethod {

	public static final String RH_URL = "https://api.robinhood.com";

	public static final int MAX_TICKERS = 1630;

	protected static final RequestManager reqMan = RequestManager.getInstance();

	protected final Configuration config;

	private String urlBase;

	/** Default request methodType is GET*/
	private RequestMethod methodType = RequestMethod.GET;

	/** The return type for the request */
	private Type returnType = null;

	/**
	 * A flag for the methodType specifying if it requires an AuthToken for the
     * user in order to be ran.
	 */
	private boolean requireToken = false;

    /** {@link HashMap} containing all of Header Parameters */
    private final HashMap<String, String> headerParameters = new HashMap<>();

    /**
     * {@link HashMap} of Route key-value pairs
     * (written in the {@link ApiMethod#urlBase} as http://url.com/{routeKey}
     */
    private final HashMap<String, String> routeParameters = new HashMap<>();

    /** {@link HashMap} of Query key-value pairs */
    private final HashMap<String, Object> queryParameters = new HashMap<>();

    /** {@link HashMap} of Field key-value pairs */
    private final HashMap<String, Object> fieldParameters = new HashMap<>();

    /**
     * Constructor which stores the service which is being used
     * @param config A {@link Configuration}
     */
    protected ApiMethod(Configuration config) {
        addHeaderParameter("Accept", "application/json");
        this.config = config;
    }

    /**
     * Execute the method request. This is alternative to using the
     * RequestManager directly.
     * @param <E> The return ApiElement
     * @return The requested ApiElement or null
     */
    public <E extends ApiElement> E execute() {
        return reqMan.makeApiRequest(this);
    }

    /**
     * A methodType which adds a HttpHeaderParameter to the API request header
     * @param key The key to map to
     * @param value The value to map
     * @author Jonathan Augustine
     */
	protected void addHeaderParameter(String key, String value) {
	    headerParameters.put(key, value);
    }

    /**
     * A methodType which adds HttpHeaderParameters to the API request header
     * @param paramMap A String-String map of Key-Val pairs
     * @author Jonathan Augustine
     */
    protected void addHeaderParameters(Map<String, String> paramMap) {
        this.headerParameters.putAll(paramMap);
    }

    /**
     * Add a route parameter.
     * (written in the {@link ApiMethod#urlBase} as http://url.com/{routeKey}
     * @param key The key to map to
     * @param value The value to map
     */
    protected void addRouteParameter(String key, String value) {
        this.routeParameters.put(key, value);
    }

    /**
     * Add multiple Route parameters from a {@link Map}
     * (written in the {@link ApiMethod#urlBase} as http://url.com/{routeKey}
     * @param paramMap A String-String map of Key-Val pairs
     */
    protected void addRoutParameters(Map<String, String> paramMap) {
        this.routeParameters.putAll(paramMap);
    }

    /**
     * Add a Query key-value pair
     * @param key The key to map to
     * @param value The value to map
     */
    protected void addQueryParameter(String key, Object value) {
        this.queryParameters.put(key, value);
    }

    /**
     * Add predefined query key-val pairs
     * @param paramMap A {@link Map} of String keys and Object values
     */
    protected void addQueryParameters(Map<String, Object> paramMap) {
        this.queryParameters.putAll(paramMap);
    }

    /**
     * Add a field key-val pair
     * @param key The key to map to
     * @param val The value to map
     */
    protected void addFieldParameter(String key, Object val) {
        this.fieldParameters.put(key, val);
    }

    /**
     * Add predefined field key-val pairs
     * @param paramMap A String-Object map of Key-Val pairs
     */
    protected void addFieldParameters(Map<String, Object> paramMap) {
        this.fieldParameters.putAll(paramMap);
    }

	/**
	 * Method which adds the Authorization Token to the HTTP request header
	 * @throws NotLoggedInException if the token does not exist or
     *              If the user is not logged in.
	 */
	public void addAuthTokenParameter() {
		addHeaderParameter("Authorization", "Token " + this.config.getToken());
	}

    /** @return the header parameters of the method */
    public HashMap<String, String> getHeaderParameters() {
        return this.headerParameters;
    }

	/** @return The route parameters of the method */
	public HashMap<String, String> getRouteParameters() {
        return routeParameters;
    }

    /** @return The query parameters of the method */
    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    /** @return The field parameters of the method */
    public Map<String, Object> getFieldParameters() {
        return fieldParameters;
    }

	/** @return {@link Type ReturnType} */
	public Type getReturnType() {
		return this.returnType;
	}

	/** @return The request {@link RequestMethod} of this ApiMethod */
	public RequestMethod getMethodType() { return this.methodType; }

	/** @return The base URL with any route parameter keys */
	public String getBaseUrl() {
		return this.urlBase;
	}

	/** Method to set the request to require an AuthToken */
	protected void requireToken() {
		this.requireToken = true;
	}

	/**
	 * @return {@code true} if this {@link ApiMethod} requires an
	 *                  authorization token (i.e. logged in instance)
	 */
	public boolean requiresToken() {
		return this.requireToken;
	}

	/**
	 * Set the request method type
	 * @param methodType The {@link RequestMethod} to set to
	 */
	protected void setMethodType(RequestMethod methodType) {
		this.methodType = methodType;
	}

	/** @param returnType The return type of the {@link ApiMethod} */
	protected void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	/**
	 * Method which sets the URL base.
	 * For example, it will probably always be "api.robinhood.com/"
	 * This exists however for flexibility
	 * @param urlBase The base URL, including any route parameters
	 */
	protected void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	/**
	 * As this is an abstract methodType, this override gets the name of the
	 * implementing class
	 * @return The classes simple-name
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
