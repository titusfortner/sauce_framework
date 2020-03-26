package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementNestingTest extends BaseTest {
    @Test
    public void locateNestedElement() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("gregory"));
        Element element = gregoryRow.element(By.tagName("td"));
        element.doesExist();

        Assert.assertEquals("Gregory House", element.getText());
    }

    @Test
    public void nestedElementRelocatesWhenStale() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("gregory"));
        Element element = gregoryRow.element(By.tagName("td"));
        element.doesExist();
        browser.refresh();

        Assert.assertEquals("Gregory House", element.getText());
    }

    @Test
    public void nestedElementDoesNotExistWithoutWaiting() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("gregory"));
        Element element = gregoryRow.element(By.id("missing"));

        Instant start = Instant.now();
        boolean exists = element.doesExist();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(exists);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void nestedElementParentDoesNotExistWithoutWaiting() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("missing"));
        Element element = gregoryRow.element(By.tagName("td"));


        Instant start = Instant.now();
        boolean exists = element.doesExist();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(exists);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void actionErrorsWhenNestedElementNeverExistsAfterWait() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("gregory"));
        Element element = gregoryRow.element(By.id("missing"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            String error = "Unable to locate element: {\"method\":\"css selector\",\"selector\":\"#missing\"}";
            Assert.assertTrue(e.getMessage().contains(error));
        }
    }

    @Test
    public void actionErrorsWhenParentElementNeverExistsAfterWait() {
        browser.goTo("http://watir.com/examples/tables.html");
        Element gregoryRow = new Element(browser, By.id("missing"));
        Element element = gregoryRow.element(By.tagName("td"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            String error = "Unable to locate element: {\"method\":\"css selector\",\"selector\":\"#missing\"}";
            Assert.assertTrue(e.getMessage().contains(error));
        }
    }
}
