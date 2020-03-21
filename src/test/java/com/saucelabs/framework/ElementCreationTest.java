package com.saucelabs.framework;

import com.saucelabs.framework.elements.Element;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementCreationTest extends BaseTest {
    @Test
    public void createsElementFromLocator() {
        Element element = browser.element(By.id("foo"));
        Assert.assertEquals(By.ById.class, element.getLocator().getClass());
    }
}
