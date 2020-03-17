package com.saucelabs.example.tests.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.example.pages.LogInPage;
import com.saucelabs.example.pages.SignUpPage;
import com.saucelabs.example.tests.BaseTest;
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

        HomePage homePage = new HomePage();
        homePage.logOut();

        Assert.assertFalse(homePage.isLoggedIn());
    }

    @Test
    public void logIn() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.visit();
        UserData userData = new UserData();
        signUpPage.signUp(userData);
        HomePage homePage = new HomePage();
        homePage.logOut();

        LogInPage logInPage = new LogInPage();
        logInPage.visit();
        logInPage.logIn(userData);

        Assert.assertTrue(homePage.isLoggedIn());
    }
}
