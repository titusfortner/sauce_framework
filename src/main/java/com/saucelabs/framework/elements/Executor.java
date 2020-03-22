package com.saucelabs.framework.elements;

import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import com.saucelabs.framework.exceptions.Exceptions;
import lombok.SneakyThrows;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Instant;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Executor {
    public static int waitTime = 10;

    void run(Element element, Runnable block) {
        try {
            locate(element);
            block.run();
        } catch (StaleElementReferenceException e) {
            element.reset();
            locate(element);
            block.run();
        }
    }

    @SneakyThrows
    Object run(Element element, Callable block) {
        try {
            locate(element);
            return block.call();
        } catch (StaleElementReferenceException e) {
            element.reset();
            locate(element);
            return block.call();
        }
    }

    @SneakyThrows
    void runWithRetries(Element element, Runnable block) {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(waitTime);
        while (true) {
            try {
                locate(element);
                block.run();
                break;
            } catch (NoSuchElementException | ElementNotEnabledException | ElementNotInteractableException | StaleElementReferenceException e) {
                if (Instant.now().toEpochMilli() > expireTime) {
                    String message = "After attempting for " + waitTime + " seconds, " + e.getMessage();
                    throw Exceptions.createWithMessage(e, message);
                }
                // This is what we get for ReadOnly Text
            } catch (InvalidElementStateException e) {
                if (Instant.now().toEpochMilli() > expireTime) {
                    String message = "After attempting for " + waitTime + " seconds, " + e.getMessage();
                    throw Exceptions.createWithMessage(e, message);
                }
            }
        }
    }

    @SneakyThrows
    Object runWithRetries(Element element, Callable block) {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(waitTime);
        while (true) {
            try {
                locate(element);
                return block.call();
            } catch (NoSuchElementException | ElementNotEnabledException | ElementNotInteractableException | StaleElementReferenceException e) {
                if (Instant.now().toEpochMilli() > expireTime) {
                    String message = "After attempting for " + waitTime + " seconds, " + e.getMessage();
                    throw Exceptions.createWithMessage(e, message);
                }
            }
        }
    }

    // This is always called from context of the Executor
    private void locate(Element element) {
        element.setWebElement(element.getDriver().findElement(element.getLocator()));
    }
}