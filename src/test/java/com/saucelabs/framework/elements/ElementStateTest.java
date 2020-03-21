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
        Element element = browser.element(By.id("foo"));

        Assert.assertFalse(element.doesExist());
    }

    @Test
    public void elementDisplayed() {
        browser.get("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_interests_dentistry"));

        Assert.assertTrue(element.isPresent());
    }

    @Test
    public void elementNotDisplayed() {
        browser.get("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_interests_dolls"));

        Assert.assertFalse(element.isPresent());
    }
}
