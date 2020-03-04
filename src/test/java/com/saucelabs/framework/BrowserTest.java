package com.saucelabs.framework;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserTest {
    @Test
    public void openChromeByDefault() {
        Browser browser = new Browser();
        Assert.assertEquals(ChromeDriver.class, browser.getDriver().getClass());
        browser.close();
    }

    @Test
    public void openFirefox() {
        Browser browser = new Browser("firefox");
        Assert.assertEquals(FirefoxDriver.class, browser.getDriver().getClass());
        browser.close();
    }

    @Test
    public void acceptsDriver() {
        ChromeDriver driver = new ChromeDriver();
        Browser browser = new Browser(driver);
        Assert.assertEquals(driver, browser.getDriver());
        browser.close();
    }
}
