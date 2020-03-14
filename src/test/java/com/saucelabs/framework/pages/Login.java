package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class Login extends PageObject {
    @Getter public String url = "https://www.saucedemo.com/";

    protected HTMLElement form = new HTMLElement(browser, By.tagName("form"));
    protected TextField username = new TextField(form, By.cssSelector(":first-child"));
    protected TextField password = new TextField(browser, By.id("password"));
    protected HTMLElement submit = new HTMLElement(browser, By.className("btn_action"));

    public static Login visit() {
        Login login = new Login();
        browser.get(login.url);
        return login;
    }

    public void login(User valid) {
        fillForm(valid);
        submit.click();
    }
}
