package com.saucelabs.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.experimental.Delegate;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {
    @Delegate
    RemoteWebDriver driver;

    public Browser() {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public Browser(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public String getName() {
        return getDriver().getCapabilities().getBrowserName();
    }
}
