package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {
    @Test
    public void createsDriver() {
        Browser browser = new Browser();
        Assert.assertEquals(ChromeDriver.class, browser.getDriver().getClass());
        browser.quit();
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

    @Test
    public void createsFirefoxDriver() {
        System.setProperty("PLATFORM", "firefox");
        Browser browser = new Browser();
        Assert.assertEquals(FirefoxDriver.class, browser.getDriver().getClass());
        browser.quit();
    }
}
