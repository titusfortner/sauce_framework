package com.saucelabs.framework.pages;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.resources.LoginPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NavigationTest extends BaseTest {
    @Before
    public void setBrowser() {
        PageObject.setBrowser(browser);
    }

    @Test
    public void navigates() {
        LoginPage LoginPage = new LoginPage();
        LoginPage.visit();
        Assert.assertTrue(LoginPage.isOnPage());
    }
}
