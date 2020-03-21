package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import org.openqa.selenium.By;

public class Element {
    @Getter private By locator;
    @Getter private Browser browser;


    public Element(Browser browser, By locator) {
        this.locator = locator;
        this.browser = browser;
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
}
