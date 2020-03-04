package com.saucelabs.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PlatformFactory {

    @Getter
    RemoteWebDriver driver;

    public PlatformFactory(String platform) {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        if (platform.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
}
