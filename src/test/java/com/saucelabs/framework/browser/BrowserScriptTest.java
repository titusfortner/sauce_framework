package com.saucelabs.framework.browser;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class BrowserScriptTest extends BaseTest {
    @Test
    public void executeScript() {
        browser.goTo("http://watir.com/examples/non_control_elements.html");
        browser.executeScript("document.getElementById('rspec').innerHTML = 'javascript text'");
        String text = browser.element(By.id("rspec")).getText();

        Assert.assertEquals("javascript text", text);
    }
}
