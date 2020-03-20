package com.saucelabs.framework;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {
    @Delegate
    @Getter
    RemoteWebDriver driver;

    public Browser() {
        this(new BrowserManagerImpl());
    }

    public Browser(BrowserManager manager) {
        driver = manager.getDriver();
    }

    public Browser(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        return getDriver().getCapabilities().getBrowserName();
    }

    public void back() {
        getDriver().navigate().back();
    }

    public void foward() {
        getDriver().navigate().forward();
    }

    public void refresh() {
        getDriver().navigate().refresh();
    }
}
