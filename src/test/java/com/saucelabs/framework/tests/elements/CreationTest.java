package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class CreationTest extends BaseTest {
    @Test
    public void createsElementFromLocator() {
        HTMLElement htmlElement = new HTMLElement(browser, By.id("foo"));

        Assert.assertEquals("org.openqa.selenium.By$ById", htmlElement.getLocator().getClass().getName());
    }

    @Test
    public void createsElementFromBrowser() {
        HTMLElement htmlElement = new HTMLElement(browser, By.id("foo"));

        Assert.assertEquals("org.openqa.selenium.By$ById", htmlElement.getLocator().getClass().getName());
    }

    @Test
    public void elementNullBeforeLocate() {
        HTMLElement htmlElement = new HTMLElement(browser, By.id("foo"));

        Assert.assertNull(htmlElement.getElement());
    }
}
