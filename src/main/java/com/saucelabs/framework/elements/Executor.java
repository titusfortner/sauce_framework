package com.saucelabs.framework.elements;

import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import com.saucelabs.framework.exceptions.Exceptions;
import lombok.SneakyThrows;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Executor {
    public static int waitTime = 10;

    static boolean isStale(WebElement element) {
        try {
            element.getAttribute("anything");
            return false;
        } catch (StaleElementReferenceException e) {
            return true;
        }
    }

    static void run(Element element, Runnable block) {
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
    static Object run(Element element, Callable<Object> block) {
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
    static void runWithRetries(Element element, Runnable block) {
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
    static Object runWithRetries(Element element, Callable<Object> block) {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(waitTime);
        while (true) {
            try {
                locate(element);
                return block.call();
            } catch (NoSuchElementException | ElementNotEnabledException | ElementNotInteractableException e) {
                if (Instant.now().toEpochMilli() > expireTime) {
                    String message = "After attempting for " + waitTime + " seconds, " + e.getMessage();
                    throw Exceptions.createWithMessage(e, message);
                }
            } catch (StaleElementReferenceException e) {
                if (Instant.now().toEpochMilli() > expireTime) {
                    String message = "After attempting for " + waitTime + " seconds, " + e.getMessage();
                    throw Exceptions.createWithMessage(e, message);
                }
                element.reset();
            }
        }
    }

    public static List<WebElement> locateAll(Element element) {
        return element.getDriver().findElements(element.getLocator());
    }

    // This is always called from context of the Executor
    private static void locate(Element element) {
        if (element.webElement == null) {
            if (element.getIndex() != 0) {
                element.setWebElement(locateAll(element).get(element.getIndex()));
            } else {
                element.setWebElement(element.getDriver().findElement(element.getLocator()));
            }
        }
    }
}