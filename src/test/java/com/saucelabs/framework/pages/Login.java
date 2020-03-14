package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import org.openqa.selenium.By;

@OnPage(url="https://www.saucedemo.com/")
public class Login extends PageObject {
    protected HTMLElement form = new HTMLElement(browser, By.tagName("form"));
    protected TextField username = new TextField(form, By.cssSelector(":first-child"));
    protected TextField password = new TextField(browser, By.id("password"));
    protected HTMLElement submit = new HTMLElement(browser, By.className("btn_action"));

    public void login(User user) {
        fillForm(user);
        submit.click();
    }
}
