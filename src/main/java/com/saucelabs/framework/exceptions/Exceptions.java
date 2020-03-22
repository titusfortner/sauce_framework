package com.saucelabs.framework.exceptions;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class Exceptions {
    public static Exception createWithMessage(Exception e, String message) {
        if (e.getClass().equals(StaleElementReferenceException.class)) {
            return new StaleElementReferenceException(message);
        }
        if (e.getClass().equals(NoSuchElementException.class)) {
            return new NoSuchElementException(message);
        }
        if (e.getClass().equals(ElementNotInteractableException.class)) {
            return new ElementNotInteractableException(message);
        }
        if (e.getClass().equals(ElementNotEnabledException.class)) {
            return new ElementNotEnabledException(message);
        }
        if (e.getClass().equals(InvalidElementStateException.class)) {
                return new InvalidElementStateException(message);
        }
        return e;
    }
}
