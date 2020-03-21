package com.saucelabs.framework.browser;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.elements.Element;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementCreationTest extends BaseTest {
    @Test
    public void createsElement() {
        By locator = By.id("foo");
        Element element = new Element(browser, locator);
        Assert.assertEquals(element, browser.element(By.id("foo")));
    }
}
