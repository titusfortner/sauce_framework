package com.saucelabs.framework.pages;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.exceptions.PageObjectException;
import com.saucelabs.framework.resources.BadTitlePage;
import com.saucelabs.framework.resources.BadURLPage;
import com.saucelabs.framework.resources.LoginPage;
import com.saucelabs.framework.resources.NoOnPage;
import com.saucelabs.framework.resources.PathPage;
import com.saucelabs.framework.resources.BadElementsPage;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class NavigationTest extends BaseTest {
    @Test
    public void navigates() {
        LoginPage LoginPage = new LoginPage();
        LoginPage.visit();
        Assert.assertTrue(LoginPage.isOnPage());
        Assert.assertEquals("https://www.saucedemo.com/", browser.getCurrentUrl());
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

        try {
            noOnPage.visit();
            fail("Expected a PageObjectException that was never thrown");
        } catch (PageObjectException ignored) {
        }
    }

    @Test
    public void navigatesWithBaseURLAndPath() {
        PathPage pathPage = new PathPage();
        pathPage.visit();

        Assert.assertTrue(pathPage.isOnPage());
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", browser.getCurrentUrl());
    }

    @Test
    public void onPageWithBadElements() {
        BadElementsPage badElementsPage = new BadElementsPage();
        badElementsPage.visit();

        Assert.assertFalse(badElementsPage.isOnPage());
    }
}
