package com.saucelabs.framework.junit;

import org.junit.rules.TestWatcher;
import org.openqa.selenium.WebDriver;

public abstract class BaseTestWatcher extends TestWatcher {
    public abstract WebDriver getDriver();
}
