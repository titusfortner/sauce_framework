package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementStateTest extends BaseTest {
    @Test
    public void elementExists() {
        browser.get("https://www.saucedemo.com");
        Element element = browser.element(By.id("user-name"));

        Assert.assertTrue(element.doesExist());
    }

    @Test
    public void elementDoesNotExist() {
        browser.get("https://www.saucedemo.com");
        Element element = browser.element(By.id("foo"));

        Assert.assertFalse(element.doesExist());
    }
}
