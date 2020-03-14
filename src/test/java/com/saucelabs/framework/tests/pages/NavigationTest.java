package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.pages.*;
import com.saucelabs.framework.tests.BaseTest;
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
        Login login = new Login();
        login.visit();
        Assert.assertTrue(login.isOnPage());
    }

    @Test
    public void badURL() {
        BadURL badURL = new BadURL();
        badURL.visit();
        Assert.assertFalse(badURL.isOnPage());
    }

    @Test
    public void badTitle() {
        BadTitle badTitle = new BadTitle();
        badTitle.visit();
        Assert.assertFalse(badTitle.isOnPage());
    }

    @Test
    public void badElements() {
        BadElements badElements = new BadElements();
        badElements.visit();
        Assert.assertFalse(badElements.isOnPage());
    }
}
