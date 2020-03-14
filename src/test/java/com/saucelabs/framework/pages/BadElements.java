package com.saucelabs.framework.pages;

import com.saucelabs.framework.elements.TextField;
import org.openqa.selenium.By;

@OnPage(elements={"username", "password"}, url="https://www.saucedemo.com/")
public class BadElements extends PageObject {
    protected TextField username = new TextField(browser, By.id("not-there"));
    protected TextField password = new TextField(browser, By.id("password"));
}
