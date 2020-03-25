package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

public class ElementCollectionTest extends BaseTest {
    @Test
    public void toListLocatesElements() {
        browser.goTo("http://watir.com/examples/collections.html");
        Element element = new Element(browser, By.tagName("span"));
        element.doesExist();

        List<Element> elementList = element.toList();

        Assert.assertEquals(element.webElement, elementList.get(0).webElement);
        Assert.assertEquals("first_span", elementList.get(1).getText());
        Assert.assertEquals("second_span", elementList.get(2).getText());
    }

    @Test
    public void toListElementRelocatesStale() {
        browser.goTo("http://watir.com/examples/collections.html");
        Element element = new Element(browser, By.tagName("span"));
        List<Element> elementList = element.toList();

        Element element1 = elementList.get(1);
        browser.refresh();

        Assert.assertEquals("first_span", element1.getText());
    }
}
