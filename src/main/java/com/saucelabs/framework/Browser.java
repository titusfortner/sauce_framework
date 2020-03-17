package com.saucelabs.framework;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
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

    public TextField textField(By locator) {
        return new TextField(this, locator);
    }

    public Element element(By locator) {
        return new Element(this, locator);
    }
}
