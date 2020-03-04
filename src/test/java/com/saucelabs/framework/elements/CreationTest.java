package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreationTest extends BaseTest {
    @Test
    public void createsElementFromLocator() {
        HTMLElement htmlElement = browser.element(By.id("foo"));

        Assert.assertEquals("org.openqa.selenium.By$ById", htmlElement.getLocator().getClass().getName());
    }

    @Test
    public void elementNullBeforeLocate() {
        HTMLElement htmlElement = browser.element(By.id("foo"));

        Assert.assertNull(htmlElement.getElement());
    }


}
