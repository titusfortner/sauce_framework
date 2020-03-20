package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
    @Test
    public void createsDriver() {
        Browser browser = new Browser();
        Assert.assertEquals(ChromeDriver.class, browser.getDriver().getClass());
        browser.close();
    }

    @Test
    public void acceptsDriver() {
        ChromeDriver driver = new ChromeDriver();
        Browser browser = new Browser(driver);
        Assert.assertEquals(driver, browser.getDriver());
        browser.close();
    }

    @Test
    public void quitsBrowser() {
        Browser browser = new Browser();
        browser.quit();
        Assert.assertNull(browser.getSessionId());
    }
}
