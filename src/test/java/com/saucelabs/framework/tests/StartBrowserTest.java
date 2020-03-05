package com.saucelabs.framework.tests;

import com.saucelabs.framework.Browser;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StartBrowserTest {
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
    public void runsOnSauce() {
        Browser browser = new Browser("sauce");
        Assert.assertEquals(RemoteWebDriver.class, browser.getDriver().getClass());
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
