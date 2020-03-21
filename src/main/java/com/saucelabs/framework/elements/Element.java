package com.saucelabs.framework.elements;

import lombok.Getter;
import org.openqa.selenium.By;

public class Element {
    @Getter private By locator;


    public Element(By locator) {
        this.locator = locator;
    }
}
