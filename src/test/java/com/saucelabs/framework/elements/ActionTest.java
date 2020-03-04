package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ActionTest extends BaseTest {
    @Test
    public void clicksElement() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = browser.element(By.className("btn_action"));
        htmlElement.click();

        Assert.assertTrue(browser.getPageSource().contains("Epic sadface"));
    }
}
