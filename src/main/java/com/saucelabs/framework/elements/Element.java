package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {
    @Getter private By locator;
    @Getter private Browser browser;
    @Setter WebElement webElement;
    private Executor executor = new Executor();


    public Element(Browser browser, By locator) {
        this.locator = locator;
        this.browser = browser;
    }

    //
    // Predicate Methods; No automatic waits
    //

    // This will always make a wire call
    public boolean doesExist() {
        reset();
        locate();
        return isLocated();
    }

    public boolean isPresent() {
        try {
            return (boolean) executor.run(this, () -> webElement.isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isStale() {
        return executor.isStale(webElement);
    }

    // Exception if doesn't exist
    public boolean isEnabled() {
        return (boolean) executor.run(this, () -> webElement.isEnabled());
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

    //
    // Information Methods
    //

    public String getText() {
        return (String) executor.runWithRetries(this, () -> webElement.getText());
    }

    public String getAttribute(String attribute) {
        return (String) executor.runWithRetries(this, () -> webElement.getAttribute(attribute));
    }

    public String getValue() {
        return getAttribute("value");
    }

    //
    // Action Methods
    //

    // TODO: Move Enabled Check to a Button subclass
    public void click() {
        executor.runWithRetries(this, this::validateEnabled);
        executor.runWithRetries(this, () -> webElement.click());
    }

    // TODO: Move this method to TextField subclass
    public void setText(String text) {
        clear();
        appendText(text);
    }

    // TODO: Move this method to TextField subclass
    public void appendText(String text) {
        executor.runWithRetries(this, () -> webElement.sendKeys(text));
    }

    // TODO: Move this method to TextField subclass
    public void clear() {
        executor.runWithRetries(this, () -> webElement.clear());
    }

    //
    // Private Methods
    //

    // Only locates if not cached
    void locate() {
        if (!isLocated()) {
            try {
                executor.run(this, () -> {});
            } catch (NoSuchElementException ignored) {
            }
        }
    }

    boolean isLocated() {
        return this.webElement != null;
    }

    void reset() {
        this.webElement = null;
    }

    void validateExistence() {
        locate();
        if (!isLocated()) {
            throw new NoSuchElementException("Cannot locate an element using " + locator);
        }
    }

    void validateEnabled() {
        locate();
        if (!isEnabled()) {
            throw new ElementNotEnabledException("Element needs to be enabled to interact with it " + locator);
        }
    }

    public WebDriver getDriver() {
        return browser.getDriver();
    }
}
