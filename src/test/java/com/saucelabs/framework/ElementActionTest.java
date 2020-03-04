package com.saucelabs.framework;

import com.saucelabs.framework.elements.HTMLElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementActionTest extends BaseTest {
    @Test
    public void clicksElement() {
        browser.get("https://www.saucedemo.com");

        HTMLElement htmlElement = browser.element(By.className("btn_action"));
        htmlElement.click();

        Assert.assertTrue(browser.getPageSource().contains("Epic sadface"));
    }
}
