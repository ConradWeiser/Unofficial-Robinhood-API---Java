package conrad.weiser.robinhood.api.request;

import com.google.gson.Gson;
import conrad.weiser.robinhood.api.ApiMethod;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;
import conrad.weiser.robinhood.api.throwables.RobinhoodApiException;
import okhttp3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;


public class RequestManager {
	
		
	/**
	 * Singleton instance of this class. 
	 * Only one instance is used for future ratelimiting support
	 */
	private static RequestManager instance = null;
	
	/**
	 * Method to get the active instance of the RequestManager.
	 * If one does not exist, it creates one 
	 */
	public static RequestManager getInstance() {
		
		if(RequestManager.instance == null) {
			
			RequestManager.instance = new RequestManager();
		}
		
		return RequestManager.instance;
	}

	public RequestManager(){
		this.okClient = new OkHttpClient();
	}

	OkHttpClient okClient;
	
	
	public <T> T makeApiRequest(ApiMethod method) throws RobinhoodApiException {
		
		T response = null;
				
		//Which request type are we using? Delegate it to the proper method
		switch(method.getMethod()) {
		case DELETE:
			break;
		case GET: response = this.makeGetRequest(method);
			break;
		case HEAD:
			break;
		case OPTIONS:
			break;
		case POST: response = this.makePostRequest(method);
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
	 * Method which uses OKHTTP to send a POST request to the specified URL saved
	 * within the APIMethod class 
	 * @throws MalformedURLException  Request URL is not formatted as a valid HTTP URL
	 */
	@SuppressWarnings("unchecked")
	private <T> T makePostRequest(ApiMethod method) throws RobinhoodApiException {
		
		RequestBody body = RequestBody.create(method.getMediaType(), method.getUrlParametersAsPostBody());
		Request.Builder request = new Request.Builder();
			
		//Append each of the headers for the method
		Iterator<HttpHeaderParameter> headerIterator = method.getHttpHeaderParameters().iterator();
		while(headerIterator.hasNext()) {
			
			HttpHeaderParameter currentHeader = headerIterator.next();
			request.addHeader(currentHeader.getKey(), currentHeader.getValue());
		}

		try {
            //Append the request body
            Request builtRequest = request.url(method.getUrl())
					.post(body)
					.build();

            //Make the request
            Response response = okClient.newCall(builtRequest).execute();

            //Parse the response with Gson
            Gson gson = new Gson();
            String responseJsonString = response.body().string();
            
            //If the response type for this is VOID (Meaning we are not expecting a response) do not
            //try to use Gson
            if(method.getReturnType() == Void.TYPE)
                return (T) Void.TYPE;

            T data = gson.fromJson(responseJsonString, method.getReturnType());
            return data;

        } catch (MalformedURLException ex) {

            System.err.println("[RobinhoodApi] Malformed request URL");

        } catch (IOException e) {
			System.err.println("[RobinhoodAPI] Error connecting to Robinhood servers");
		}

		throw new RobinhoodApiException();
		
	}
	
	/**
	 * Method which uses Unirest to send a GET request to the specified URL saved
	 * within the ApiMethod class 
	 * @throws MalformedURLException Request URL is not formatted as a valid HTTP URL
	 */
	private <T> T makeGetRequest(ApiMethod method) throws RobinhoodApiException {

		RequestBody body = RequestBody.create(method.getMediaType(), method.getUrlParametersAsPostBody());
		Request.Builder request = new Request.Builder();

		//Append each of the headers for the method
		Iterator<HttpHeaderParameter> headerIterator = method.getHttpHeaderParameters().iterator();
		while(headerIterator.hasNext()) {

			HttpHeaderParameter currentHeader = headerIterator.next();
			request.addHeader(currentHeader.getKey(), currentHeader.getValue());
		}

		try {

			//Append the request body
			Request builtRequest = request.url(method.getUrl())
					.get()
					.build();

			//Make the request
			Response response = okClient.newCall(builtRequest).execute();

			//Parse the response with Gson
			Gson gson = new Gson();
			String responseJsonString = response.body().string();

			T data = gson.fromJson(responseJsonString, method.getReturnType());

			return data;

		} catch (MalformedURLException e) {

			System.err.println("[RobinhoodAPI] Malformed request URL");

		} catch (IOException e) {
			System.err.println("[RobinhoodApi] Failed to communicate with Robinhood servers, request failed");
		}

		throw new RobinhoodApiException();

	}
	
	

}
