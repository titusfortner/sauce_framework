package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.exceptions.ElementNotEnabledException;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Element {
    @Getter private int index = 0;
    @Getter private Element scope;
    @Getter private By locator;
    @Getter private Browser browser;
    @Setter @Getter WebElement webElement;
    @Getter private Actions actions;


    public Element(Browser browser, By locator) {
        this.locator = locator;
        this.browser = browser;
        this.actions = new Actions(browser.getDriver());
    }

    private Element(Browser browser, By locator, int index) {
        this(browser, locator);
        this.index = index;
    }

    private Element(Element element, By locator) {
        this(element.getBrowser(), locator);
        this.scope = element;
    }

    //
    // Predicate Methods; No automatic waits
    //

    // This will always make a wire call
    public boolean doesExist() {
        reset();
        try {
            Executor.run(this, () -> {});
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed() {
        try {
            return (boolean) Executor.run(this, () -> webElement.isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Exception if doesn't exist
    public boolean isEnabled() {
        return (boolean) Executor.run(this, () -> webElement.isEnabled());
    }

    public boolean isStale() {
        try {
            // This can be any wire call
            Executor.forceRun(this, () -> webElement.getCssValue("stale-check"));
            return false;
        } catch (StaleElementReferenceException e) {
            return true;
        }
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
                return c.getLocator().equals(locator) && c.getIndex() == index;
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    //
    // Information Methods
    //

    public String getText() {
        return (String) Executor.runWithRetries(this, () -> webElement.getText());
    }

    public String getAttribute(String attribute) {
        return (String) Executor.runWithRetries(this, () -> webElement.getAttribute(attribute));
    }

    public String getValue() {
        return getAttribute("value");
    }

    public String getStyle(String property) {
        return (String) Executor.runWithRetries(this, () -> webElement.getCssValue(property));
    }

    //
    // Action Methods
    //

    protected void scrollElement() {
        Executor.runWithRetries(this, () ->
            browser.executeScript("arguments[0].scrollIntoView(true);", webElement));
    }

    // TODO: Move Enabled Check to a Button subclass
    public void click() {
        Executor.runWithRetries(this, this::validateEnabled);
        Executor.runWithRetries(this, () -> webElement.click());
    }

    // TODO: Move this method to TextField subclass
    public void setText(String text) {
        clear();
        appendText(text);
    }

    // TODO: Move this method to TextField subclass
    public void appendText(String text) {
        Executor.runWithRetries(this, () -> webElement.sendKeys(text));
    }

    // TODO: Move this method to TextField subclass
    public void clear() {
        Executor.runWithRetries(this, () -> webElement.clear());
    }

    //
    // Action Class Methods
    //

    public void hover() {
        scrollElement();
        Executor.runWithRetries(this, () ->  actions.moveToElement(webElement).perform());
    }

    public void doubleClick() {
        scrollElement();
        Executor.runWithRetries(this, () ->  actions.doubleClick(webElement).perform());
    }

    public void rightClick() {
        scrollElement();
        Executor.runWithRetries(this, () ->  actions.contextClick(webElement).perform());
    }

    public void dragAndDrop(Element other) {
        scrollElement();
        Executor.runWithRetries(other, () -> {});
        Executor.runWithRetries(this, () -> {
            actions.dragAndDrop(webElement, other.getWebElement()).perform();
        });
    }

    public void dragAndDropBy(int xOffset, int yOffset) {
        scrollElement();
        Executor.runWithRetries(this, () ->  actions.dragAndDropBy(webElement, xOffset, yOffset).perform());
    }

    //
    // Private Methods
    //

    void reset() {
        if (scope != null) {
            scope.reset();
        }
        this.webElement = null;
    }

    void validateEnabled() {
        if (!isEnabled()) {
            throw new ElementNotEnabledException("Element needs to be enabled to interact with it " + locator);
        }
    }

    public WebDriver getDriver() {
        return browser.getDriver();
    }

    public void locate() {
        if (webElement == null) {
            if (scope != null) {
                scope.locate();
                setWebElement(scope.webElement.findElement(locator));
            } else if (index != 0) {
                setWebElement(locateAll().get(index));
            } else {
                setWebElement(getDriver().findElement(locator));
            }
        }
    }

    public List<WebElement> locateAll() {
        return getDriver().findElements(locator);
    }

    public List<Element> toList() {
        List<WebElement> webElements = locateAll();
        List<Element> elements = new ArrayList<>();

        IntStream.range(0, webElements.size()).forEach(index -> {
            Element element = new Element(browser, locator, index);
            element.setWebElement(webElements.get(index));
            elements.add(element);
        });
        return elements;
    }

    public Element element(By locator) {
        return new Element(this, locator);
    }

    public void setText(Keys keyboardKey) {
        Executor.runWithRetries(this, () ->  actions.sendKeys(keyboardKey).perform());
    }
}
