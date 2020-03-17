package com.saucelabs.framework.pages;

import com.saucelabs.framework.elements.TextField;
import org.openqa.selenium.By;

@OnPage(elements={"username", "password"}, url="https://www.saucedemo.com/")
public class BadElements extends PageObject {
    private TextField username = browser.textField(By.id("not-there"));
    private TextField password = browser.textField(By.id("password"));
}
