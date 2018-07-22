package com.ampro.robinhood.endpoint.account.data;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.util.ChronoFormatter;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * This method returns SEC Rule 405 related information.
 */
public class AccountHolderAffiliation implements ApiElement {

	private boolean control_person;
	private String control_person_security_symbol;
	private boolean object_to_disclosure;
	private String security_affiliated_address;
	private boolean security_affiliated_employee;
	private String security_affiliated_firm_name;
	private String security_affiliated_firm_relationship;
	private String security_affiliated_person_name;
	private boolean sweep_consent;

	private String updated_at;
	private URL user;

	@Override
	public boolean requiresAuth() { return true; }

	public ZonedDateTime getUpdatedAt() {
		return ChronoFormatter.parseDefault(this.updated_at);
	}

	/** @return the control_person */
	public boolean isControlPerson() {
		return control_person;
	}

	/**
	 * @return the control_person_security_symbol
	 */
	public String getControlPersonSecuritySymbol() {
		return control_person_security_symbol;
	}

	/**
	 * @return the object_to_disclosure
	 */
	public boolean isObjectsToDisclosure() {
		return object_to_disclosure;
	}

	/**
	 * @return the security_affiliated_address
	 */
	public String getSecurityAffiliatedAddress() {
		return security_affiliated_address;
	}

	/**
	 * @return the security_affiliated_employee
	 */
	public boolean isSecurityAffiliatedEmployee() {
		return security_affiliated_employee;
	}

	/**
	 * @return the security_affiliated_firm_name
	 */
	public String getSecurityAffiliatedFirmName() {
		return security_affiliated_firm_name;
	}

	/**
	 * @return the security_affiliated_firm_relationship
	 */
	public String getSecurityAffiliatedFirm_Relationship() {
		return security_affiliated_firm_relationship;
	}

	/**
	 * @return the security_affiliated_person_name
	 */
	public String getSecurityAffiliatedPersonName() {
		return security_affiliated_person_name;
	}

	/**
	 * @return the sweep_consent
	 */
	public boolean isSweepConsent() {
		return sweep_consent;
	}

	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}

}
