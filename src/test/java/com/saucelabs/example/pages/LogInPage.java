package com.saucelabs.example.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;

@OnPage(path="/sign_in")
public class LogInPage extends BasePage {

    private TextField email = browser.textField(By.id("session_email"));
    private TextField password = browser.textField(By.id("session_password"));
    private Element submit = browser.element(By.cssSelector("[data-test=submit]"));

    public void logIn(UserData userData) {
        fillForm(userData);
        submit.click();
    }
}
