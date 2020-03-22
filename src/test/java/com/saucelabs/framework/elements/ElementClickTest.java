package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementClickTest extends BaseTest {
    @Test
    public void clickElementExistsAndVisible() {
        browser.get("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("best_language"));

        element.click();
        Assert.assertEquals("Ruby!", element.getText());
    }

    @Test()
    public void clickElementExistsAndEventuallyVisibleAfterWait() {
        browser.get("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        browser.element(By.id("show_bar")).click();
        element.click();
        Instant finish = Instant.now();

        long duration = Duration.between(start, finish).toMillis();
        System.out.println(duration);
        Assert.assertTrue(duration > 600);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickElementEventuallyExistsThenEventuallyVisibleAfterWaits() {
        browser.get("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("foobar"));

        Instant start = Instant.now();
        browser.element(By.id("add_foobar")).click();
        element.click();
        Instant finish = Instant.now();

        long duration = Duration.between(start, finish).toMillis();
        System.out.println(duration);
        Assert.assertTrue(duration > 2000);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickElementPresentAndEventuallyEnabledAfterWait() {
        browser.get("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("btn"));

        Instant start = Instant.now();
        browser.element(By.id("enable_btn")).click();
        element.click();
        Instant finish = Instant.now();

        long duration = Duration.between(start, finish).toMillis();
        System.out.println(duration);
        Assert.assertTrue(duration > 600);
        Assert.assertTrue(duration < 5000);
    }

    @Test()
    public void clickErrorsWhenElementPresentAndNeverEnabledAfterWait() {
        browser.get("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("btn"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (ElementNotEnabledException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void clickErrorsWhenElementNeverExistsAfterWait() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void clickErrorsWhenElementExistsButNeverVisibleAfterWait() {
        browser.get("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (ElementNotInteractableException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }
}
