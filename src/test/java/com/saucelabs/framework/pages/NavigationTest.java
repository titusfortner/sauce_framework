package com.saucelabs.framework.pages;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.resources.BadTitlePage;
import com.saucelabs.framework.resources.BadURLPage;
import com.saucelabs.framework.resources.LoginPage;
import com.saucelabs.framework.resources.NoOnPage;
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

    @Test
    public void badURL() {
        BadURLPage badURLPage = new BadURLPage();
        badURLPage.visit();
        Assert.assertFalse(badURLPage.isOnPage());
    }

    @Test
    public void badTitle() {
        BadTitlePage badTitlePage = new BadTitlePage();
        badTitlePage.visit();
        Assert.assertFalse(badTitlePage.isOnPage());
    }

    @Test
    public void noOnPageNoVisit() {
        NoOnPage noOnPage = new NoOnPage();

        noOnPage.visit();
    }
}
