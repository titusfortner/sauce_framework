package com.saucelabs.example.pages;

import com.saucelabs.example.data.UserData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

public class HeaderSection extends PageObject {

    protected Element logOut = new Element(browser, By.cssSelector("[data-test=sign-out]"));
    protected Element currentUser = new Element(browser, By.cssSelector("[data-test=current-user]"));

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
