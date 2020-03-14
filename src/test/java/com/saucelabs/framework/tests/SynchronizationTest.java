package com.saucelabs.framework.tests;

import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.fail;

public class SynchronizationTest extends BaseTest {
    @Test
    public void waitsForMissingElement() {
        System.setProperty("automatic.wait", "1");
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.id("not_there"));
        long beforeTime = System.currentTimeMillis();
        try {
            htmlElement.click();
            fail();
        } catch (NoSuchElementException e) {
            long ms = System.currentTimeMillis() - beforeTime;
            Assert.assertTrue(ms > 1000);
        }
    }

    @Test
    public void doesNotWaitForFoundElement() {
        System.setProperty("automatic.wait", "5");
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = new HTMLElement(browser, By.className("btn_action"));
        long beforeTime = System.currentTimeMillis();
        htmlElement.click();
        long ms = System.currentTimeMillis() - beforeTime;
        Assert.assertTrue(ms < 5000);
    }

}
