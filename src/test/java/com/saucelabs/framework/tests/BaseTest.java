package com.saucelabs.framework.tests;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.pages.PageObject;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public Browser browser;

    @Before
    public void setup() {
        browser = new Browser();
        PageObject.setBrowser(browser);
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
