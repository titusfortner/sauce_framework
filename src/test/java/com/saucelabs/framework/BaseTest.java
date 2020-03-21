package com.saucelabs.framework;

import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public Browser browser;

    @Before
    public void setup() {
        browser = new Browser();
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
