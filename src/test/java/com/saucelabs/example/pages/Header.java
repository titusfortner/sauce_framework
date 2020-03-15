package com.saucelabs.example.pages;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

public class Header extends PageObject {
    protected Element logOut = new Element(browser, By.cssSelector("[data-test=sign-out]"));

    public void logOut() {
        logOut.click();
    }

    public boolean isLoggedIn() {
        return logOut.doesExist();
    }
}
