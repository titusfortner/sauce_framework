package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class TextFieldTest extends BaseTest {
    @Test
    public void sendKeys() {
        browser.get("https://www.saucedemo.com");

        TextField textField = new TextField(browser, By.id("user-name"));

        textField.sendKeys("Foo");

        Assert.assertEquals("Foo", textField.get());
    }

    @Test
    public void set() {
        browser.get("https://www.saucedemo.com");

        TextField textField = new TextField(browser, By.id("user-name"));

        textField.sendKeys("Bar");
        textField.set("Foo");

        Assert.assertEquals("Foo", textField.getValue());
    }

    @Test
    public void clear() {
        browser.get("https://www.saucedemo.com");

        TextField textField = new TextField(browser, By.id("user-name"));

        textField.sendKeys("Bar");
        textField.clear();

        Assert.assertEquals("", textField.getValue());
    }

}
