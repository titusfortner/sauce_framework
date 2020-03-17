package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import org.openqa.selenium.By;

@OnPage(url="https://www.saucedemo.com/")
public class Login extends PageObject {
    private Element form = browser.element(By.tagName("form"));
    private TextField username = new TextField(form, By.cssSelector(":first-child"));
    private TextField password = browser.textField(By.id("password"));
    private Element submit = browser.element(By.className("btn_action"));

    public void login(User user) {
        fillForm(user);
        submit.click();
    }
}
