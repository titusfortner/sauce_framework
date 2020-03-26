package com.saucelabs.framework.browser;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class BrowserInformationTest extends BaseTest {

    @Test
    public void getCurrentUrl() {
        browser.goTo("https://www.saucedemo.com/");
        Assert.assertEquals("https://www.saucedemo.com/", browser.getCurrentUrl());
    }

    @Test
    public void getTitle() {
        browser.goTo("https://www.saucedemo.com/");
        Assert.assertEquals("Swag Labs", browser.getTitle());
    }

    @Test
    public void getName() {
        Assert.assertEquals("chrome", browser.getName());
    }

    @Test
    public void getPageSource() {
        Assert.assertEquals("<html><head></head><body></body></html>", browser.getText());
    }
}
