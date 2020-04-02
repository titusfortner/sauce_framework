package com.saucelabs.framework;

import com.saucelabs.framework.elements.Element;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Browser {
    @Getter RemoteWebDriver driver;

    //
    // Constructors
    //

    public Browser() {
        this(new BrowserManagerImpl());
    }

    public Browser(BrowserManager manager) {
        driver = manager.getDriver();
    }

    public Browser(RemoteWebDriver driver) {
        this.driver = driver;
    }

    //
    // Information
    //

    public String getName() {
        return getDriver().getCapabilities().getBrowserName();
    }

    public String getHTML() {
        return getDriver().getPageSource();
    }

    public String getText() {
        return getHTML();
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    //
    // Navigation
    //

    public void goTo(String url) {
        getDriver().navigate().to(url);
    }

    public void goTo(URL url) {
        getDriver().navigate().to(url);
    }

    public void back() {
        getDriver().navigate().back();
    }

    public void forward() {
        getDriver().navigate().forward();
    }

    public void refresh() {
        getDriver().navigate().refresh();
    }

    //
    // Actions
    //

    public Object executeScript(String script, Object... args) {
        return getDriver().executeScript(script, args);
    }

    public void quit() {
        getDriver().quit();
    }

    //
    // Element Generation
    //

    public Element element(By locator) {
        return new Element(this, locator);
    }


}
