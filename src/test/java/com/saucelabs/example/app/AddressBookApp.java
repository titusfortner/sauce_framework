package com.saucelabs.example.app;

import com.saucelabs.example.apis.Authentication;
import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.HomePage;
import com.saucelabs.framework.pages.PageObject;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

public class AddressBookApp {
    public void authenticateNewUser(UserData userData) {
        HomePage homePage = new HomePage();
        homePage.visit();

        Authentication authentication = new Authentication();
        Response response = authentication.create(userData);
        String rememberToken = response.getCookies().get("remember_token");

        Cookie cookie = new Cookie("remember_token", rememberToken);
        PageObject.getBrowser().manage().addCookie(cookie);
    }

    public UserData authenticateNewUser() {
        UserData userData = new UserData();
        authenticateNewUser(userData);
        return userData;
    }
}
