package com.saucelabs.framework;

import com.saucelabs.framework.elements.HTMLElement;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Browser {
    @Delegate @Getter private WebDriver driver;

    public Browser(String platform) {
        PlatformFactory factory = new PlatformFactory(platform);
        this.driver = factory.getDriver();
    }

    public Browser() {
        this("chrome");
    }

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public HTMLElement element(By locator) {
        return new HTMLElement(this, locator);
    }
}
