package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class PredicateTest extends BaseTest {
    @Test
    public void elementExists() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.className("btn_action"));

        Assert.assertTrue(element.doesExist());
    }

    @Test
    public void elementDoesNotExist() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.className("not_there"));

        Assert.assertFalse(element.doesExist());
    }

    @Test
    public void elementIsVisible() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.className("btn_action"));

        Assert.assertTrue(element.isVisible());
    }

    @Test
    public void elementIsNotVisible() {
        browser.get("http://watir.com/examples/forms_with_input_elements.html");

        Element element = browser.element(By.id("wants_newsletter"));

        Assert.assertFalse(element.isVisible());
    }

    @Test
    public void nonExistentElementNotVisible() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.className("not_there"));

        Assert.assertFalse(element.isVisible());
    }
}
