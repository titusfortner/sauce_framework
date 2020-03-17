package com.saucelabs.example.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.framework.elements.Element;
import org.openqa.selenium.By;

public class HeaderSection extends BasePage {

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
