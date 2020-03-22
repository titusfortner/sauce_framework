package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementInformationTest extends BaseTest {
    @Test
    public void getTextElementExistsAndVisible() {
        browser.get("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("footer"));

        Assert.assertEquals("This is a footer.", element.getText());
    }

    @Test()
    public void getTextErrorsWhenElementNeverExistsAfterWait() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.getText();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);
        }
    }

    // TODO - need to update testing site to just add exists not exists & visible combo
    @Test()
    public void getTextElementEventuallyExistsAfterWait() {
        browser.get("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("foobar"));

        Instant start = Instant.now();
        browser.element(By.id("add_foobar")).click();
        String text = element.getText();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertEquals("", text);
        Assert.assertTrue(duration > 600);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void getTextIsEmptyWhenElementExistsAndNotVisibleWithoutWaiting() {
        browser.get("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        String text = element.getText();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertTrue(text.isEmpty());
        Assert.assertTrue(duration < 5000);
    }
}
