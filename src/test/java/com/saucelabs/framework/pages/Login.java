package com.saucelabs.framework.pages;

import lombok.Getter;


public class Login extends PageObject {
    @Getter public String url = "https://www.saucedemo.com/";

    public static Login visit() {
        Login login = new Login();
        browser.get(login.url);
        return login;
    }
}
