package com.saucelabs.framework.exceptions;

import org.openqa.selenium.InvalidElementStateException;

public class ElementNotEnabledException extends InvalidElementStateException {
    public ElementNotEnabledException(String message) {
        super(message);
    }

    public ElementNotEnabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
