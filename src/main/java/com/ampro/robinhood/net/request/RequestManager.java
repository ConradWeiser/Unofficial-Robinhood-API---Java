package com.ampro.robinhood.net.request;

import com.ampro.robinhood.RobinhoodApi;
import com.ampro.robinhood.net.ApiMethod;
import com.google.gson.Gson;
import io.github.openunirest.http.HttpResponse;
import io.github.openunirest.http.JsonNode;
import io.github.openunirest.http.Unirest;
import io.github.openunirest.http.exceptions.UnirestException;
import io.github.openunirest.request.HttpRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Singleton for making HTTP(S) requests with {@link ApiMethod}
 * @author Conrad Weise, modified by Jonathan Augustine
 */
public class RequestManager {

    private static final Gson gson = new Gson();

	/**
	 * Singleton instance of this class.
	 * Only one instance is used for future ratelimiting support
	 */
	private static RequestManager instance;

	/**
	 * The active instance of the RequestManager.
	 * If one does not exist, it creates one
     * @return active instance of the RequestManager
	 */
	public static RequestManager getInstance() {
		if(RequestManager.instance == null) {
            //All methods get json responses
            Unirest.setHttpClient(HttpClients.createDefault());
		    Unirest.setDefaultHeader("Accept", "appliation/json");
			RequestManager.instance = new RequestManager();
		}
		return RequestManager.instance;
	}

	/**
	 * Make an API request to the Robinhood servers
     *
	 * @param method The ApiMethod containing the request information
	 * @param <T> The return type
	 * @return The Http response as the ApiMethod's {@link ApiMethod#returnType}
	 * 	           or null if an error response is received.
	 */
	public <T> T makeApiRequest(ApiMethod method) {

		T response = null;

		//Which request type are we using? Delegate it to the proper method
		switch(method.getMethodType()) {
			case GET:
				response = this.makeGetRequest(method);
				break;
			case POST:
				response = this.makePostRequest(method);
				break;
			case DELETE:
				break;
			case HEAD:
				break;
			case OPTIONS:
				break;
			case PUT:
				break;
			case TRACE:
				break;
			default:
				break;
		}

		return response;
	}

	/**
	 * Method which uses HTTP to send a POST request to the specified URL saved
	 * within the APIMethod class
     *
     * @param method The ApiMethod making the request
     * @param <T> The return type
     * @return The Http response as the ApiMethod's {@link ApiMethod#returnType}
     * 	  	           or null if an error response is received.
	 */
	private <T> T makePostRequest(ApiMethod method) {
        HttpRequest request = Unirest.post(method.getBaseUrl())
                                     .headers(method.getHeaderParameters())
                                     .queryString(method.getQueryParameters())
                                     .fields(method.getFieldParameters())
                                     .getHttpRequest();
        method.getRouteParameters().forEach(request::routeParam);

        return makeRequest(request, method);
    }

	/**
	 * Method which uses Unirest to send a GET request to the specified URL saved
	 * within the ApiMethod class
     *
     * @param method The ApiMethod making the request
     * @param <T> The return type
     * @return The Http response as the ApiMethod's {@link ApiMethod#returnType}
     * 	  	           or null if an error response is received.
     */
	private <T> T makeGetRequest(ApiMethod method) {

        HttpRequest request =
                Unirest.get(method.getBaseUrl())
                       .headers(method.getHeaderParameters())
                       .queryString(method.getQueryParameters())
                       .getHttpRequest();
        method.getRouteParameters().forEach(request::routeParam);

        return makeRequest(request, method);
	}

	/**
	 * Makes a request to the API.
     *
	 * @param request The HttpRequest
	 * @param method The ApiMethod
	 * @param <T> The return type
	 * @return The Http response as the ApiMethod's {@link ApiMethod#returnType}
	 *          or null if an error response is received.
	 */
	@SuppressWarnings("unchecked")
	private <T> T makeRequest(HttpRequest request, ApiMethod method) {
		T out = null;
		try {
			//Make the request
			HttpResponse<JsonNode> response = request.asJson();
			if (!Objects.equals(response.getStatusText(), "OK")) {
				return null;
			}
			//If the response type for this is VOID (
			//Meaning we are not expecting a response) do not
			//try to use Gson
			if (method.getReturnType() == Void.TYPE) {
				return (T) Void.TYPE;
			}

			String body = IOUtils.toString(response.getRawBody(),
			                               StandardCharsets.UTF_8.name());
			out = gson.fromJson(body, method.getReturnType());

		} catch (UnirestException ex) {
			System.err.println("[RobinhoodApi] Failed to communicate with endpoint");
			RobinhoodApi.log.throwing(this.getClass().getName(), "makeRequest", ex);
		} catch (IOException ex) {
			System.err.println("[RobinhoodApi] Failed to parse response body");
			RobinhoodApi.log.throwing(this.getClass().getName(), "makeRequest", ex);
		}
		return out;
	}

}
