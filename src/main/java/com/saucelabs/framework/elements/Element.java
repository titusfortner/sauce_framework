package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Getter
public class Element {
    private Element scope;
    private By locator;
    private Browser browser;
    private WebElement element;
    Synchronizer synchronizer;

    public Element(Browser browser, By locator) {
        this.browser = browser;
        this.locator = locator;
        this.synchronizer = new Synchronizer();
    }

    public Element(Element element, By locator) {
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

    public String getText() {
        return synchronizer.text(this, () -> getElement().getText());
    }

    public String getValue() {
        return getAttribute("value");
    }

    public String getAttribute(String attribute) {
        return synchronizer.text(this, () -> getElement().getAttribute(attribute));
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

    public TextField textField(By locator) {
        return new TextField(this, locator);
    }

    public Element element(By locator) {
        return new Element(this, locator);
    }
}
