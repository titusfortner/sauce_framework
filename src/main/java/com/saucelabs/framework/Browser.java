package com.saucelabs.framework;

import lombok.Getter;
import lombok.experimental.Delegate;
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
}
