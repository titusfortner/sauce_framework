package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public class LocationTest extends BaseTest {
    @Test
    public void locatesElementFromAction() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.id("user-name"));

        htmlElement.click();

        Assert.assertEquals(RemoteWebElement.class, htmlElement.getElement().getClass());
    }
}
