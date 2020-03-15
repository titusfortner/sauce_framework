package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class CreationTest extends BaseTest {
    @Test
    public void createsElementFromLocator() {
        Element element = new Element(browser, By.id("foo"));

        Assert.assertEquals("org.openqa.selenium.By$ById", element.getLocator().getClass().getName());
    }

    @Test
    public void createsElementFromBrowser() {
        Element element = new Element(browser, By.id("foo"));

        Assert.assertEquals("org.openqa.selenium.By$ById", element.getLocator().getClass().getName());
    }

    @Test
    public void elementNullBeforeLocate() {
        Element element = new Element(browser, By.id("foo"));

        Assert.assertNull(element.getElement());
    }
}
