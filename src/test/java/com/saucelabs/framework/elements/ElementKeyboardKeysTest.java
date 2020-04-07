package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ElementKeyboardKeysTest extends BaseTest {
    @Test
    public void enterKeyWorks() {
        browser.goTo("https://the-internet.herokuapp.com/key_presses");

        Element body = browser.element(By.tagName("body"));
        body.click();
        body.sendKeyboardKeys(Keys.ENTER);
        Element result = browser.element(By.id("result"));

        Assert.assertEquals("You entered: ENTER", result.getText());
    }
    @Test(expected = ComparisonFailure.class)
    public void incorrectKeyboardKeyFails() {
        browser.goTo("https://the-internet.herokuapp.com/key_presses");

        Element body = browser.element(By.tagName("body"));
        body.click();
        body.sendKeyboardKeys(Keys.ARROW_LEFT);
        Element result = browser.element(By.id("result"));

        Assert.assertEquals("You entered: ENTER", result.getText());
    }
}
