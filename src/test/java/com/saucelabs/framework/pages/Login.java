package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import org.openqa.selenium.By;

@OnPage(url="https://www.saucedemo.com/")
public class Login extends PageObject {
    protected Element form = new Element(browser, By.tagName("form"));
    protected TextField username = new TextField(form, By.cssSelector(":first-child"));
    protected TextField password = new TextField(browser, By.id("password"));
    protected Element submit = new Element(browser, By.className("btn_action"));

    public void login(User user) {
        fillForm(user);
        submit.click();
    }
}
