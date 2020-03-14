package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class HTMLElement {
    private HTMLElement scope;
    private By locator;
    private Browser browser;
    private WebElement element;
    Synchronizer synchronizer;

    public HTMLElement(Browser browser, By locator) {
        this.browser = browser;
        this.locator = locator;
        this.synchronizer = new Synchronizer();
    }

    public HTMLElement(HTMLElement element, By locator) {
        this(element.getBrowser(), locator);
        this.scope = element;
    }

    //
    // PREDICATE METHODS
    //

    // This always checks instead of relying on cache
    public boolean doesExist() {
        reset();
        try {
            locate();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public Boolean isVisible() {
        return doesExist() && element.isDisplayed();
    }

    //
    // ACTION METHODS
    //

    public void click() {
        synchronizer.act(this, () -> getElement().click());
    }

    //
    // INFORMATION METHODS
    //

    public String getAttribute(String attribute) {
        return this.element.getAttribute(attribute);
    }

    public String getValue() {
        return getAttribute("value");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof HTMLElement)) {
            return false;
        } else {
            try {
                HTMLElement c = (HTMLElement) o;
                return c.getLocator().equals(locator);
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    //
    // RESTRICTED METHODS
    //

    void locate() {
        if (this.element == null) {
            if (this.scope == null) {
                this.element = browser.getDriver().findElement(locator);
            } else {
                scope.locate();
                WebElement parent = scope.getElement();
                this.element = parent.findElement(locator);
            }
        }
    }

    void reset() {
        this.element = null;
    }
}
