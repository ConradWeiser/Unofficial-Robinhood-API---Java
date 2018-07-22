package com.ampro.robinhood.endpoint.account;

import com.ampro.robinhood.PrivateBaseTest;
import org.junit.Test;

public class AccountsTest extends PrivateBaseTest {
    @Test
    public void getAccounts() {
        loggedInApi.getAccountData();
    }
}
