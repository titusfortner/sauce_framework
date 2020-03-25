package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementStateTest extends BaseTest {
    @Test
    public void elementExists() {
        browser.goTo("https://www.saucedemo.com");
        Element element = browser.element(By.id("user-name"));

        Assert.assertTrue(element.doesExist());
    }

    @Test
    public void elementDoesNotExistWithoutWaiting() {
        Element element = browser.element(By.id("foo"));

        Instant start = Instant.now();
        boolean exists = element.doesExist();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(exists);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void elementPresentWhenDisplayed() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_interests_dentistry"));

        Assert.assertTrue(element.isPresent());
    }

    @Test
    public void elementPresentWhenStale() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_interests_dentistry"));
        element.doesExist();

        browser.refresh();

        Assert.assertTrue(element.isPresent());
    }

    @Test
    public void elementNotPresentWhenNotExistWithoutWaiting() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        boolean present = element.isPresent();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(present);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void elementNotPresentWhenExistsAndNotDisplayedWithoutWaiting() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_interests_dolls"));

        Instant start = Instant.now();
        boolean present = element.isPresent();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(present);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void elementEnabled() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_occupation"));

        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void elementEnabledWhenStale() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_occupation"));
        element.doesExist();

        browser.refresh();

        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void elementNotEnabledWithoutWaiting() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_species"));

        Instant start = Instant.now();
        boolean enabled = element.isEnabled();
        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();

        Assert.assertFalse(enabled);
        Assert.assertTrue(duration < 5000);
    }

    @Test
    public void elementEnabledErrorsWhenNotExistWithoutWaiting() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.isEnabled();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration < 5000);
        }
    }

    @Test
    public void elementStale() {
        browser.goTo("https://www.saucedemo.com");
        Element element = browser.element(By.id("user-name"));
        element.doesExist();
        browser.refresh();

        Assert.assertTrue(element.isStale());
    }

    @Test
    public void elementNotStale() {
        browser.goTo("https://www.saucedemo.com");
        Element element = browser.element(By.id("user-name"));
        element.doesExist();

        Assert.assertFalse(element.isStale());
    }
}
