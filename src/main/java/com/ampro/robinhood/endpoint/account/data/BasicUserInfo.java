package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElement;

import java.net.URL;

public class BasicUserInfo implements ApiElement {

	private String username;
	private String first_name;
	private String last_name;
	private URL id_info;
	private URL url;
	private URL basic_info;
	private String email;
	private URL investment_profile;
	private String id;
	private URL international_info;
	private URL employment;
	private URL additional_info;

	@Override
	public boolean requiresAuth() { return true; }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the first_name
	 */
	public String getFirstName() {
		return first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLastName() {
		return last_name;
	}
	/**
	 * @return the id_info
	 */
	public URL getIdInfo() {
		return id_info;
	}
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * @return the basic_info
	 */
	public URL getBasicInfo() {
		return basic_info;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the investment_profile
	 */
	public URL getInvestmentProfile() {
		return investment_profile;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the international_info
	 */
	public URL getInternationalInfo() {
		return international_info;
	}
	/**
	 * @return the employment
	 */
	public URL getEmployment() {
		return employment;
	}
	/**
	 * @return the additional_info
	 */
	public URL getAdditionalInfo() {
		return additional_info;
	}

}
