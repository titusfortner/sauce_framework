package com.saucelabs.example.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;

@OnPage(path="/sign_up")
public class SignUpPage extends BasePage {

    protected TextField email = new TextField(browser, By.id("user_email"));
    protected TextField password = new TextField(browser, By.id("user_password"));
    protected Element submit = new Element(browser, By.cssSelector("[data-test=submit]"));

    public void signUp(UserData userData) {
        fillForm(userData);
        submit.click();
    }

    public void signUp() {
        signUp(new UserData());
    }
}
