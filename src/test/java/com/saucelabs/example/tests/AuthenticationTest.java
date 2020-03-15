package com.saucelabs.example.tests;

import com.saucelabs.example.data.User;
import com.saucelabs.example.pages.Header;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.example.pages.LogIn;
import com.saucelabs.example.pages.SignUp;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signUp() {
        SignUp signUp = new SignUp();
        signUp.visit();

        signUp.signUp();

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isOnPage());
    }

    @Test
    public void logOut() {
        SignUp signUp = new SignUp();
        signUp.visit();
        User user = new User();
        signUp.signUp(user);

        Header header = new Header();
        header.logOut();

        Assert.assertFalse(header.isLoggedIn());
    }

    @Test
    public void logIn() {
        SignUp signUp = new SignUp();
        signUp.visit();
        User user = new User();
        signUp.signUp(user);
        Header header = new Header();
        header.logOut();

        LogIn logIn = new LogIn();
        logIn.visit();
        logIn.logIn(user);

        Assert.assertTrue(header.isLoggedIn());
    }
}
