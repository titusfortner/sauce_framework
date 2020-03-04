package com.saucelabs.framework;

import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementCreationTest {
    @Test
    public void createsElementFromLocator() {
        Browser browser = new Browser();

        HTMLElement htmlElement = browser.element(By.id("foo"));
        Assert.assertEquals("org.openqa.selenium.By$ById", htmlElement.getLocator().getClass().getName());

        browser.quit();
    }

    @Test
    public void elementNullBeforeLocate() {
        Browser browser = new Browser();

        HTMLElement htmlElement = browser.element(By.id("foo"));
        Assert.assertNull(htmlElement.getElement());

        browser.quit();
    }


}
