package com.saucelabs.framework.elements;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class Synchronizer {
    public static int timeout = 30;

    void act(Element element, Runnable block) {
        do {
            try {
                element.validateExistence();
                block.run();
                break;
            } catch (NoSuchElementException e) {
                waitUntilExists(element);
            } catch (ElementNotInteractableException e) {
                waitUntilPresent(element);
            }
        } while (true);
    }

    private void waitUntilExists(Element element) {
        try {
            await().atMost(timeout, SECONDS).until(element::doesExist);
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException("This element was not located: " + element.getLocator().toString());
        }
    }

    private void waitUntilPresent(Element element) {
        try {
            await().atMost(timeout, SECONDS).until(element::isPresent);
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException("This element was located, but is not displayed: " + element.getLocator().toString());
        }
    }
}