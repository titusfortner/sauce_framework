package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;

public class BrowserNavigationTest extends BaseTest {

    @Test
    public void get() {
        browser.get("https://www.saucedemo.com/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
