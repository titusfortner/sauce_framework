package com.saucelabs.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class PlatformFactory {

    private String username = System.getenv("SAUCE_USERNAME");
    private String accessKey = System.getenv("SAUCE_ACCESS_KEY");
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public PlatformFactory(String platform) {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        if (platform.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (platform.equals("sauce")) {
            URL url = null;
            try {
                url = new URL("https://" + username + ":" + accessKey + "@ondemand.us-west-1.saucelabs.com/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setCapability("platformName", "Windows 10");
            browserOptions.setCapability("browserVersion", "latest");
            driver.set(new RemoteWebDriver(url, browserOptions));
        } else {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
