package com.saucelabs.framework;

import com.saucelabs.framework.elements.Executor;
import com.saucelabs.framework.pages.PageObject;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public Browser browser;

    @Before
    public void setup() {
        browser = new Browser();
        PageObject.setBrowser(browser);
        Executor.waitTime = 5;
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
