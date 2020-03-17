package com.saucelabs.example.tests.apis;

import com.saucelabs.example.app.AddressBookApp;
import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.HeaderSection;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationAPITest extends BaseTest {
    @Test
    public void authenticateNewUser() {
        AddressBookApp application = new AddressBookApp();
        application.authenticateNewUser();

        HeaderSection headerSection = new HeaderSection();
        Assert.assertTrue(headerSection.isLoggedIn());
    }

    @Test
    public void authenticateProvidedUser() {
        HomePage homePage = new HomePage();
        homePage.visit();

        AddressBookApp application = new AddressBookApp();
        UserData userData = new UserData();
        application.authenticateNewUser(userData);

        HeaderSection headerSection = new HeaderSection();
        Assert.assertTrue(headerSection.isLoggedIn(userData));
    }
}
