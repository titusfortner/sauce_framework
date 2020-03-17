package com.saucelabs.example.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;

@OnPage(path="/")
public class HomePage extends BasePage {
    private Element logOut = browser.element(By.cssSelector("[data-test=sign-out]"));
    private Element currentUser = browser.element(By.cssSelector("[data-test=current-user]"));

    public void logOut() {
        logOut.click();
    }

    public boolean isLoggedIn() {
        return logOut.doesExist();
    }

    public boolean isLoggedIn(UserData userData) {
        return userData.getEmail().equals(currentUser.getText());
    }
}
