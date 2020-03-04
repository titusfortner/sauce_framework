package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;

public class BrowserActionsTest {
    @Test
    public void navigates() {
        Browser browser = new Browser();
        browser.get("https://www.saucedemo.com/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/");
        browser.quit();
    }

}
