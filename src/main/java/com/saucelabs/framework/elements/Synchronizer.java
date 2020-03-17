package com.saucelabs.framework.elements;

import lombok.SneakyThrows;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class Synchronizer {
    public int timeout;

    public Synchronizer() {
        this(desiredWait());
    }

    public Synchronizer(Integer i) {
        this.timeout = i;
    }

    void act(Element element, Runnable block) {
        do {
            try {
                element.locate();
                block.run();
                break;
            } catch (NoSuchElementException e) {
                waitUntilExists(element);
                break;
            } catch (ElementNotVisibleException e) {
                waitUntilVisible(element);
                break;
            } catch (StaleElementReferenceException ignored) {
                element.reset();
            }
        } while (true);
    }

    @SneakyThrows
    String text(Element element, Callable<String> block) {
        do {
            try {
                element.locate();
                return block.call();
            } catch (NoSuchElementException e) {
                waitUntilExists(element);
                break;
            } catch (ElementNotVisibleException e) {
                waitUntilVisible(element);
                break;
            } catch (StaleElementReferenceException ignored) {
            }
        } while (true);
        return null;
    }

    private static Integer desiredWait() {
        // TODO: this needs a better way to get set
        String propertyWait = System.getProperty("automatic.wait");
        return propertyWait == null ? 30 : Integer.parseInt(propertyWait);
    }

    private void waitUntilExists(Element element) {
        try {
            await().atMost(timeout, SECONDS).until(element::doesExist);
        } catch (ConditionTimeoutException e) {
            throw new NoSuchElementException("This element was not located: " + element.getLocator().toString());
        }
    }

    private void waitUntilVisible(Element element) {
        try {
            await().atMost(timeout, SECONDS).until(element::isVisible);
        } catch (ConditionTimeoutException e) {
            throw new ElementNotVisibleException("This element was located, but is not displayed: " + element.getLocator().toString());
        }
    }
}