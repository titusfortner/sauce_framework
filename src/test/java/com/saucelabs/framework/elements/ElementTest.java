package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementTest extends BaseTest {
    @Test
    public void createdElementStoresLocator() {
        By locator = By.id("foo");
        Element element = new Element(browser, locator);
        Assert.assertEquals(locator, element.getLocator());
    }

    @Test
    public void createdElementStoresBrowser() {
        Element element = new Element(browser, By.id("foo"));
        Assert.assertEquals(browser, element.getBrowser());
    }

    @Test
    public void elementsEqualWithSameLocator() {
        Element element1 = new Element(browser, By.id("foo"));
        Element element2 = new Element(browser, By.id("foo"));
        Assert.assertEquals(element1, element2);
    }
}
