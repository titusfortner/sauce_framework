package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementActionTest extends BaseTest {
    @Before
    public void synch() {
        Synchronizer.timeout = 5;
    }

    @Test
    public void clickElementPresent() {
        browser.get("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("best_language"));

        Instant start = Instant.now();
        element.click();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        System.out.println(duration);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickElementEventuallyDisplayed() {
        browser.get("http://watir.com/examples/wait.html");
        browser.element(By.id("show_bar")).click();

        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        element.click();
        Instant finish = Instant.now();

        long duration = Duration.between(start, finish).toMillis();
        System.out.println(duration);
        Assert.assertTrue(duration > 500);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickElementEventuallyExistThenDisplayed() {
        browser.get("http://watir.com/examples/wait.html");
        browser.element(By.id("add_foobar")).click();

        Element element = browser.element(By.id("foobar"));

        Instant start = Instant.now();
        element.click();
        Instant finish = Instant.now();

        long duration = Duration.between(start, finish).toMillis();
        System.out.println(duration);
        Assert.assertTrue(duration > 2000);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickElementNeverPresent() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (TimeoutException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);
        }
    }
}
