package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ElementActionsTest extends BaseTest {

    @Test
    public void hovers() {
        browser.goTo("http://watir.com/examples/hover.html");
        Element link = browser.element(By.tagName("a"));
        link.hover();
        Assert.assertEquals("20px", link.getStyle("font-size"));
    }

    @Test
    public void doubleClicks() {
        browser.goTo("http://watir.com/examples/non_control_elements.html");
        Element div = browser.element(By.id("html_test"));
        div.doubleClick();

        String message = browser.element(By.id("messages")).getText();

        Assert.assertEquals("double clicked", message);
    }

    @Test
    public void rightClicks() {
        browser.goTo("http://watir.com/examples/right_click.html");

        Element div = browser.element(By.id("click"));
        div.rightClick();

        String message = browser.element(By.id("messages")).getText();

        Assert.assertEquals("right-clicked", message);
    }

    @Test
    public void dragsAndDropsElement() {
        browser.goTo("http://watir.com/examples/drag_and_drop.html");

        Element draggable = browser.element(By.id("draggable"));
        Element droppable = browser.element(By.id("droppable"));

        draggable.dragAndDrop(droppable);

        String message = droppable.getText();

        Assert.assertEquals("Dropped!", message);
    }

    @Test
    public void dragsAndDropsOffset() {
        browser.goTo("http://watir.com/examples/drag_and_drop.html");

        Element draggable = browser.element(By.id("draggable"));
        Element droppable = browser.element(By.id("droppable"));

        draggable.dragAndDropBy(200, 50);

        String message = droppable.getText();

        Assert.assertEquals("Dropped!", message);
    }
}
