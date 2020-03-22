package com.saucelabs.framework;

import com.saucelabs.framework.elements.Executor;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public Browser browser;

    @Before
    public void setup() {
        browser = new Browser();
        Executor.waitTime = 5;
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
