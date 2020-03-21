package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Element {
    @Getter private By locator;
    @Getter private Browser browser;
    protected WebElement webElement;


    public Element(Browser browser, By locator) {
        this.locator = locator;
        this.browser = browser;
    }

    // This will always make a wire call
    public boolean doesExist() {
        reset();
        locate();
        return isLocated();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!this.getClass().isInstance(o)) {
            return false;
        } else {
            try {
                Element c = (Element) o;
                return c.getLocator().equals(locator);
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    private void locate() {
        if (this.webElement == null) {
            try {
                this.webElement = browser.getDriver().findElement(locator);
            } catch (NoSuchElementException ignored) {
            }
        }
    }

    private boolean isLocated() {
        return this.webElement != null;
    }

    private void reset() {
        this.webElement = null;
    }
}