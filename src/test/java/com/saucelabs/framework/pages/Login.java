package com.saucelabs.framework.pages;

import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;


@Getter
public class Login extends PageObject {
    public String url = "https://www.saucedemo.com/";
    private HTMLElement submit = element(() -> browser.element(By.className("btn_action")));
    private TextField userName = textField(() -> browser.textField(By.id("user-name")));

    public static Login visit() {
        Login login = new Login();
        browser.get(login.url);
        return login;
    }
}