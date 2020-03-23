package com.saucelabs.framework.pages;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.resources.LoginPage;
import com.saucelabs.framework.resources.UserData;
import org.junit.Assert;
import org.junit.Test;

public class ElementTest extends BaseTest {

    @Test
    public void actsOnElements() {
        PageObject.setBrowser(browser);

        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertFalse(loginPage.isOnPage());
    }

    @Test
    public void usesDataObject() {
        UserData userData = UserData.valid();
        LoginPage loginPage = new LoginPage();
        loginPage.visit();

        loginPage.login((userData));

        Assert.assertFalse(loginPage.isOnPage());
    }
}
