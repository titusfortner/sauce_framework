package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import org.openqa.selenium.By;

public class TextField extends HTMLElement {

    public TextField(Browser browser, By locator) {
        super(browser, locator);
    }

    public TextField(HTMLElement element, By locator) {
        super(element, locator);
    }

    public void set(String text) {
        clear();
        sendKeys(text);
    }

    public void clear() {
        synchronizer.act(this, () -> getElement().clear());
    }

    public void sendKeys(String text) {
        synchronizer.act(this, () -> getElement().sendKeys(text));
    }

    public String get() {
        return getValue();
    }
}
