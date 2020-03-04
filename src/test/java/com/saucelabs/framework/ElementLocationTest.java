package com.saucelabs.framework;

import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public class ElementLocationTest extends BaseTest {
    @Test
    public void locatesElement() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = browser.element(By.id("user-name"));

        htmlElement.locate();

        Assert.assertEquals(RemoteWebElement.class, htmlElement.getElement().getClass());
    }
}
