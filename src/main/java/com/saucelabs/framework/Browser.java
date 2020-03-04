package com.saucelabs.framework;

import com.saucelabs.framework.elements.HTMLElement;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {
    @Delegate @Getter private RemoteWebDriver driver;

    public Browser(String platform) {
        PlatformFactory factory = new PlatformFactory(platform);
        this.driver = factory.getDriver();
    }

    public Browser() {
        this("chrome");
    }

    public Browser(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HTMLElement element(By locator) {
        return new HTMLElement(locator);
    }
}
