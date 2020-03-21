package com.saucelabs.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Setter;
import lombok.SneakyThrows;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.Map;

public class BrowserManagerImpl implements BrowserManager {
    @Setter String platformConfig = "./src/main/java/com/saucelabs/framework/config/platform.yml";
    @Setter String driverPath = "lib/drivers/auto/";

    @SneakyThrows
    public RemoteWebDriver getDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();

        Map<String, Object> platform = getYamlMap().get(getPlatform());

        for (String key : platform.keySet()) {
            capabilities.setCapability(key, platform.get(key));
        }

        setDriverManager(capabilities.getBrowserName());
        return createDriver(capabilities);
    }

    private RemoteWebDriver createDriver(MutableCapabilities capabilities) {
        String browserName = capabilities.getBrowserName();
        setDriverManager(browserName);
        switch (browserName) {
            case "firefox":
                return new FirefoxDriver(new FirefoxOptions(capabilities));
            case "Microsoft WebDriver":
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions.merge(capabilities));
            case "internet explorer":
                return new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
            case "safari":
                return new SafariDriver(new SafariOptions(capabilities));
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions.merge(capabilities));
        }
    }

    private void setDriverManager(String browserName) {
        System.setProperty("wdm.targetPath", driverPath);
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "Microsoft WebDriver":
                WebDriverManager.edgedriver().setup();
                break;
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                break;
            case "safari":
                break;
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }
    }

    @SneakyThrows
    private Map<String, Map<String, Object>> getYamlMap() {
        Yaml yaml = new Yaml();
        return yaml.load(new FileReader(platformConfig));
    }

    private String getPlatform() {
        if (System.getProperty("PLATFORM") != null) {
            return System.getProperty("PLATFORM");
        } else if (System.getenv("PLATFORM") != null) {
            return System.getenv("PLATFORM");
        } else {
            return "default";
        }
    }
}
