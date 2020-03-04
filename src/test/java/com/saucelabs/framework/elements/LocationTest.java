package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public class LocationTest extends BaseTest {
    @Test
    public void locatesElementFromAction() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = browser.element(By.id("user-name"));

        htmlElement.click();

        Assert.assertEquals(RemoteWebElement.class, htmlElement.getElement().getClass());
    }
}
