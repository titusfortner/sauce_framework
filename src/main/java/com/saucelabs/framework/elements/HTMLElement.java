package com.saucelabs.framework.elements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HTMLElement {
    @Getter private By locator;
    @Getter private WebElement element;


    public HTMLElement(By locator) {
        this.locator = locator;
    }
}
