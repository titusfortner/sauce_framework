package com.saucelabs.framework.elements;

import com.saucelabs.framework.BaseTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.Instant;

import static junit.framework.TestCase.fail;

public class ElementSendKeysTest extends BaseTest {

    @Test
    public void clearElementTextField() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_occupation"));

        element.clear();
        Assert.assertTrue(element.getValue().isEmpty());
    }

    // TODO - create element for this
//    @Test()
//    public void clearElementPresentAndEventuallyReadableAfterWait() {
//        browser.goTo("http://watir.com/examples/wait.html");
//
//        Element element = browser.element(By.id("btn"));
//
//        Instant start = Instant.now();
//        browser.element(By.id("enable_btn")).clear();
//        element.clear();
//        Instant finish = Instant.now();
//
//        long duration = Duration.between(start, finish).toMillis();
//        System.out.println(duration);
//        Assert.assertTrue(duration > 600);
//        Assert.assertTrue(duration < 5000);
//    }

    @Test()
    public void clearTextErrorsWhenElementNotTextField() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("messages"));

        Instant start = Instant.now();
        try {
            element.clear();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (InvalidElementStateException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void clearErrorsWhenElementDisabledAfterWait() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_species"));

        Instant start = Instant.now();
        try {
            element.clear();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (InvalidElementStateException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    @Ignore("flaky")
    public void clearErrorsWhenElementNotReadableAfterWait() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("good_luck"));

        Instant start = Instant.now();
        try {
            element.clear();
            fail("Expected a Timeout Exception that was never thrown");
        } catch (InvalidElementStateException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void clearErrorsWhenElementNeverExistsAfterWait() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.clear();
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test
    public void appendText() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_occupation"));

        element.appendText("s are boring");
        Assert.assertEquals("Developers are boring", element.getValue());
    }

    // TODO - create element for this
//    @Test()
//    public void appendTextElementPresentAndEventuallyReadableAfterWait() {
//        browser.goTo("http://watir.com/examples/wait.html");
//
//        Element element = browser.element(By.id("btn"));
//
//        Instant start = Instant.now();
//        browser.element(By.id("enable_btn")).appendText();
//        element.appendText();
//        Instant finish = Instant.now();
//
//        long duration = Duration.between(start, finish).toMillis();
//        System.out.println(duration);
//        Assert.assertTrue(duration > 600);
//        Assert.assertTrue(duration < 5000);
//    }

    @Test()
    public void appendTextErrorsWhenElementNotTextField() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("messages"));

        Instant start = Instant.now();
        try {
            element.appendText("Not Writeable");
            fail("Expected a Timeout Exception that was never thrown");
        } catch (InvalidElementStateException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void appendTextErrorsWhenElementDisabledAfterWait() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_species"));

        Instant start = Instant.now();
        try {
            element.appendText("Can't Do It");
            fail("Expected a ElementNotInteractableException that was never thrown");
        } catch (ElementNotInteractableException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void appendTextErrorsWhenElementReadOnlyTextField() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("good_luck"));

        Instant start = Instant.now();
        try {
            element.appendText("Not Writeable");
            fail("Expected a Timeout Exception that was never thrown");
        } catch (InvalidElementStateException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test()
    public void appendTextErrorsWhenElementNeverExistsAfterWait() {
        Element element = browser.element(By.id("not-there"));

        Instant start = Instant.now();
        try {
            element.appendText("Never There");
            fail("Expected a NoSuchElementException that was never thrown");
        } catch (NoSuchElementException e) {
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            Assert.assertTrue(duration > 5000);

            Assert.assertTrue(e.getMessage().contains("After attempting for"));
        }
    }

    @Test
    public void setText() {
        browser.goTo("http://watir.com/examples/forms_with_input_elements.html");
        Element element = browser.element(By.id("new_user_occupation"));

        element.setText("Just This");
        Assert.assertEquals("Just This", element.getValue());
    }
}
