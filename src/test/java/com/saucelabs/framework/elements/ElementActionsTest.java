package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementActionsTest extends BaseTest {

    @Test
    public void elementCanHover() {
        browser.goTo("https://the-internet.herokuapp.com/hovers");
        Element element = browser.element(By.xpath("//*[@src='/img/avatar-blank.jpg']"));
        Element onHoverElement = browser.element(By.xpath("//h5[text()='name: user1']"));
        element.hover();
        Assert.assertTrue("Expected the element to be visible on hover", onHoverElement.isDisplayed());
    }
}
