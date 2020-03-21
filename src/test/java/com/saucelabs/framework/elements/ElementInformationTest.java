package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class ElementInformationTest extends BaseTest {
    @Test
    public void getText() {
        browser.get("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("footer"));

        Assert.assertEquals("This is a footer.", element.getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void getTextNotPresent() {
        Element element = browser.element(By.id("not-there"));

        element.getText();
    }
}
