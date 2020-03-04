package com.saucelabs.framework;

import lombok.experimental.Delegate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {
    @Delegate
    RemoteWebDriver driver;

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
}
