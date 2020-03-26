package com.saucelabs.framework.browser;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class BrowserNavigationTest extends BaseTest {

    @Test
    public void get() {
        browser.goTo("https://www.saucedemo.com/");
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void forward() {
        browser.goTo("https://www.saucedemo.com/");
        browser.goTo("https://www.google.com/");
        browser.back();
        browser.forward();

        Assert.assertEquals(browser.getCurrentUrl(), "https://www.google.com/");
    }

    @Test
    public void back() {
        browser.goTo("https://www.saucedemo.com/");
        browser.goTo("https://www.google.com/");
        browser.back();

        Assert.assertEquals(browser.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void refresh() {
        browser.goTo("https://www.saucedemo.com/");
        browser.getDriver().findElement(By.className("btn_action")).click();
        browser.refresh();

        Assert.assertEquals(0, browser.getDriver().findElements(By.className("error-button")).size());
    }
}
