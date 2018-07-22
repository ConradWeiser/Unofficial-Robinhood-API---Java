package com.ampro.robinhood.endpoint.authorize.methods;

import com.ampro.robinhood.endpoint.authorize.data.AuthorizationData;
import com.ampro.robinhood.endpoint.authorize.data.AuthorizationData.Token;
import com.ampro.robinhood.throwables.RobinhoodApiException;

/**
 * Get an authorization token from an account with multifactor authorization login.
 *
 * @author Jonathan Augustine
 */
public class AuthorizeWithMultifactor extends GetAuthorizationData {

    /**
     * Get an authorization token from an account with multifactor authorization login.
     *
     * @param email The email used to log into Robinhood
     * @param password The password
     */
    public AuthorizeWithMultifactor(String email, String password,
                                    AuthorizationData authData)
    throws RobinhoodApiException {
        super(email, password);
        if (authData.getMfaCode() == null) {
            throw new RobinhoodApiException("no multifactor code");
        }
        this.addFieldParameter("mfa_code", authData.getMfaCode());
        this.setReturnType(Token.class);
    }

    /**
     * Get an authorization token from an account with multifactor authorization login.
     *
     * @param email The email used to log into Robinhood
     * @param password The password
     */
    public AuthorizeWithMultifactor(String email, String password, String mfaCode) {
        super(email, password);
        this.addFieldParameter("mfa_code", mfaCode);
        this.setReturnType(Token.class);
    }

}
