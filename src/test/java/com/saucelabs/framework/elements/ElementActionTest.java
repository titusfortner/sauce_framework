package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class ElementActionTest extends BaseTest {
    @Test
    public void clickElementPresent() {
        browser.get("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("best_language"));
        element.click();

        Assert.assertEquals("Ruby!", element.getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void clickElementNotPresent() {
        Element element = browser.element(By.id("not-there"));

        element.click();
    }
}
