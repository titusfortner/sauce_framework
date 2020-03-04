package com.saucelabs.framework.elements;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class HTMLElement {
    private By locator;
    private Browser browser;
    private WebElement element;


    public HTMLElement(Browser browser, By locator) {
        this.browser = browser;
        this.locator = locator;
    }

    public void click() {
        locate();
        getElement().click();
    }

    void locate() {
        if (this.element == null) {
            this.element = browser.getDriver().findElement(locator);
        }
    }
}
