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

    static void forceRun(Element element, Runnable block) {
        element.locate();
        block.run();
    }

    @SneakyThrows
    static Object forceRun(Element element, Callable<Object> block) {
        element.locate();
        return block.call();
    }

    static void run(Element element, Runnable block) {
        try {
            forceRun(element, block);
        } catch (StaleElementReferenceException e) {
            element.reset();
            forceRun(element, block);
        }
    }

    @SneakyThrows
    static Object run(Element element, Callable<Object> block) {
        try {
            return forceRun(element, block);
        } catch (StaleElementReferenceException e) {
            element.reset();
            return forceRun(element, block);
        }
    }

    @SneakyThrows
    static void runWithRetries(Element element, Runnable block) {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(waitTime);
        while (true) {
            try {
                forceRun(element, block);
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
                return forceRun(element, block);
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
}