package com.saucelabs.example.tests.apis;

import com.saucelabs.example.apis.AuthenticationAPI;
import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationAPITest extends BaseTest {
    @Test
    public void authenticateNewUser() {
        AuthenticationAPI authenticationAPI = new AuthenticationAPI();
        UserData userData = authenticationAPI.createUser();

        HomePage homePage = new HomePage();
        homePage.visit();
        homePage.addCookie("remember_token", userData.getId());
        homePage.visit();

        Assert.assertTrue(homePage.isLoggedIn());
    }

    @Test
    public void authenticateProvidedUser() {
        AuthenticationAPI authenticationAPI = new AuthenticationAPI();
        UserData userData = new UserData();
        UserData createdUser = authenticationAPI.createUser(userData);

        HomePage homePage = new HomePage();
        homePage.visit();
        homePage.addCookie("remember_token", createdUser.getId());
        homePage.visit();

        Assert.assertTrue(homePage.isLoggedIn(userData));
    }
}
