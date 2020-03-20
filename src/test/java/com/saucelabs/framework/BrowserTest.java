package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
    @Test
    public void createWithDriver() {
        Browser browser = new Browser();
        Assert.assertEquals(ChromeDriver.class, browser.getDriver().getClass());
        browser.close();
    }
}
