package com.saucelabs.example.pages;

import com.saucelabs.example.data.User;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

@OnPage(url="http://a.testaddressbook.com/sign_in")
public class LogIn extends PageObject {
    protected TextField email = new TextField(browser, By.id("session_email"));
    protected TextField password = new TextField(browser, By.id("session_password"));
    protected Element submit = new Element(browser, By.cssSelector("[data-test=submit]"));

    public void logIn(User user) {
        fillForm(user);
        submit.click();
    }
}
