package com.saucelabs.framework.tests;

import org.junit.Assert;
import org.junit.Test;

public class BrowserActionsTest extends BaseTest {
    @Test
    public void navigates() {
        browser.get("https://www.saucedemo.com/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/");
    }

}
