package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class PredicateTest extends BaseTest {
    @Test
    public void elementExists() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.className("btn_action"));

        Assert.assertTrue(htmlElement.doesExist());
    }

    @Test
    public void elementDoesNotExist() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.className("not_there"));

        Assert.assertFalse(htmlElement.doesExist());
    }

    @Test
    public void elementIsVisible() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.className("btn_action"));

        Assert.assertTrue(htmlElement.isVisible());
    }

    @Test
    public void elementIsNotVisible() {
        browser.get("http://watir.com/examples/forms_with_input_elements.html");

        HTMLElement htmlElement = new HTMLElement(browser, By.id("wants_newsletter"));

        Assert.assertFalse(htmlElement.isVisible());
    }

    @Test
    public void nonExistentElementNotVisible() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.className("not_there"));

        Assert.assertFalse(htmlElement.isVisible());
    }
}
