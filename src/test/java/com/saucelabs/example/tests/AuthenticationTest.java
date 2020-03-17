package com.saucelabs.example.tests;

import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.HeaderSection;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.example.pages.LogInPage;
import com.saucelabs.example.pages.SignUpPage;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signUp() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.visit();

        signUpPage.signUp();

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isOnPage());
    }

    @Test
    public void logOut() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.visit();
        UserData userData = new UserData();
        signUpPage.signUp(userData);

        HeaderSection headerSection = new HeaderSection();
        headerSection.logOut();

        Assert.assertFalse(headerSection.isLoggedIn());
    }

    @Test
    public void logIn() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.visit();
        UserData userData = new UserData();
        signUpPage.signUp(userData);
        HeaderSection headerSection = new HeaderSection();
        headerSection.logOut();

        LogInPage logInPage = new LogInPage();
        logInPage.visit();
        logInPage.logIn(userData);

        Assert.assertTrue(headerSection.isLoggedIn());
    }
}
