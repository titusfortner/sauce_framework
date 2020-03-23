package com.saucelabs.framework.resources;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

@OnPage(url="https://www.saucedemo.com/")
public class LoginPage extends PageObject {
    private Element usernameField = browser.element(By.id("user-name"));
    private Element passwordField = browser.element(By.id("password"));
    private Element submitButton = browser.element(By.className("btn_action"));

    public void login(String username, String password) {
        usernameField.setText(username);
        passwordField.setText(password);
        submitButton.click();
    }
}
