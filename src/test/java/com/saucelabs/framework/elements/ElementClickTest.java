package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;

public class ElementClickTest extends BaseTest {
    @Test
    public void clickElementExistsAndVisible() {
        browser.goTo("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("best_language"));

        element.click();
        Assert.assertEquals("Ruby!", element.getText());
    }

    @Test
    public void clickElementWhenStale() {
        browser.goTo("http://watir.com/examples/non_control_elements.html");
        Element element = browser.element(By.id("best_language"));
        element.doesExist();

        browser.refresh();

        try {
            element.click();
            Assert.assertTrue(true);
        } catch (StaleElementReferenceException e) {
            fail("Click should re-look up stale elements");
        }
    }

    @Test()
    public void clickElementExistsAndEventuallyVisibleAfterWait() {
        browser.goTo("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        browser.element(By.id("show_bar")).click();
        element.click();
        Instant finish = Instant.now();

        Integer duration = getDuration(start, finish);
        System.out.println(duration);
        assertGreaterThan(duration, 600);
        assertThat(duration, Matchers.lessThan(5000));
    }

    private void assertGreaterThan(Integer duration, int i) {
        assertThat(duration, Matchers.greaterThan(i));
    }

    private int getDuration(Instant start, Instant finish) {
        return Math.toIntExact(Duration.between(start, finish).toMillis());
    }

    @Test()
    public void clickElementEventuallyExistsThenEventuallyVisibleAfterWaits() {
        browser.goTo("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("foobar"));

        Instant start = Instant.now();
        browser.element(By.id("add_foobar")).click();
        element.click();
        Instant finish = Instant.now();

        Integer duration = getDuration(start, finish);
        System.out.println(duration);
        assertThat(duration, Matchers.greaterThan(2000));
        assertThat(duration, Matchers.lessThan(5000));
    }

    @Test()
    public void clickElementPresentAndEventuallyEnabledAfterWait() {
        browser.goTo("http://watir.com/examples/wait.html");

        Element element = browser.element(By.id("btn"));

        Instant start = Instant.now();
        browser.element(By.id("enable_btn")).click();
        element.click();
        Instant finish = Instant.now();

        Integer duration = getDuration(start, finish);

        System.out.println(duration);
        assertThat(duration, Matchers.greaterThan(600));
        assertThat(duration, Matchers.lessThan(5000));
    }

    @Test()
    public void clickErrorsWhenElementPresentAndNeverEnabledAfterWait() {
        browser.goTo("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("btn"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (ElementNotEnabledException e) {
            Instant finish = Instant.now();
            Integer duration = getDuration(start, finish);

            assertThat(duration, Matchers.greaterThan(5000));
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
            Integer duration = getDuration(start, finish);

            assertThat(duration, Matchers.greaterThan(5000));
            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void clickErrorsWhenElementExistsButNeverVisibleAfterWait() {
        browser.goTo("http://watir.com/examples/wait.html");
        Element element = browser.element(By.id("bar"));

        Instant start = Instant.now();
        try {
            element.click();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (ElementNotInteractableException e) {
            Instant finish = Instant.now();
            Integer duration = getDuration(start, finish);

            assertThat(duration, Matchers.greaterThan(5000));
            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }
}
