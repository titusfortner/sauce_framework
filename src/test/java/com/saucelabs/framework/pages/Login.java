package com.saucelabs.framework.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;


@Getter
public class Login extends PageObject {
    public String url = "https://www.saucedemo.com/";
    private TextField username = textField(() -> browser.textField(By.id("user-name")));
    private TextField password = textField(() -> browser.textField(By.id("password")));
    private HTMLElement submit = element(() -> browser.element(By.className("btn_action")));

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
