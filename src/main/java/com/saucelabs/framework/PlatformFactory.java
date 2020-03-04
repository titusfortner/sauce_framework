package com.saucelabs.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class PlatformFactory {

    @Getter
    RemoteWebDriver driver;
    private String username = System.getenv("SAUCE_USERNAME");
    private String accessKey = System.getenv("SAUCE_ACCESS_KEY");

    public PlatformFactory(String platform) {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        if (platform.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
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
            driver = new RemoteWebDriver(url, browserOptions);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
}
