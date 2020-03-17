package com.saucelabs.framework.tests.elements;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ActionTest extends BaseTest {
    @Test
    public void clicksElement() {
        browser.get("https://www.saucedemo.com");

        Element element = browser.element(By.className("btn_action"));
        element.click();

        Assert.assertTrue(browser.getPageSource().contains("Epic sadface"));
    }
}
