package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;


public class Login extends PageObject {
    @Getter public String url = "https://www.saucedemo.com/";
    @Getter private TextField username = new TextField(browser, By.id("user-name"));
    @Getter private TextField password = new TextField(browser, By.id("password"));
    @Getter private HTMLElement submit = new HTMLElement(browser, By.className("btn_action"));

    public static Login visit() {
        Login login = new Login();
        browser.get(login.url);
        return login;
    }

    public void login(User valid) {
        username.sendKeys(valid.getUsername());
        password.sendKeys(valid.getPassword());
        submit.click();
    }
}
