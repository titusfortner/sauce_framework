package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public class LocationTest extends BaseTest {
    @Test
    public void locatesElementFromAction() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.id("user-name"));

        element.click();

        Assert.assertEquals(RemoteWebElement.class, element.getElement().getClass());
    }
}
