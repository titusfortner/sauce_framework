package com.saucelabs.framework.browser;

import com.saucelabs.framework.Browser;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

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
        browser.quit();
    }

    @Test
    public void quitsBrowser() {
        Browser browser = new Browser();
        browser.quit();
        Assert.assertNull(browser.getDriver().getSessionId());
    }

// This needs to get mocked out to run in parallel
//    @Test
//    public void createsFirefoxDriver() {
//        System.setProperty("PLATFORM", "firefox");
//        Browser browser = new Browser();
//        System.clearProperty("PLATFORM");
//        Assert.assertEquals(FirefoxDriver.class, browser.getDriver().getClass());
//        browser.quit();
//    }
}
