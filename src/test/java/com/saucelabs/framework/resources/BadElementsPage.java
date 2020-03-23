package com.saucelabs.framework.resources;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

@OnPage(url="https://www.saucedemo.com/", elements={"usernameField", "notThere"})
public class BadElementsPage extends PageObject {
    private Element usernameField = browser.element(By.id("user-name"));
    private Element notThere = browser.element(By.id("not-there"));
}
