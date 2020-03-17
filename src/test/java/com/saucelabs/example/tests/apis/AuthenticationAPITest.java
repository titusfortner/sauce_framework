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

        HomePage homePage = new HomePage();
        homePage.visit();

        HeaderSection headerSection = new HeaderSection();
        Assert.assertTrue(headerSection.isLoggedIn());
    }

    @Test
    public void authenticateProvidedUser() {
        AddressBookApp application = new AddressBookApp();
        UserData userData = new UserData();
        application.authenticateNewUser(userData);

        HomePage homePage = new HomePage();
        homePage.visit();

        HeaderSection headerSection = new HeaderSection();
        Assert.assertTrue(headerSection.isLoggedIn(userData));
    }
}
