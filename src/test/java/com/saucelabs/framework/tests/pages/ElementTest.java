package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.Login;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.HashSet;

public class ElementTest extends BaseTest {

    @Test
    public void createsKeySet() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("username");
        hashSet.add("password");
        hashSet.add("submit");
        hashSet.add("form");

        Login login = new Login();
        Assert.assertEquals(hashSet, login.getElements());
    }

    @Test
    public void getsValue() {
        Login login = new Login();
        TextField createdElement = new TextField(browser, By.id("password"));

        TextField receivedElement = (TextField) login.getElement("password");
        Assert.assertEquals(createdElement, receivedElement);
    }

    @Test
    public void locatesElements() {
        PageObject.setBrowser(browser);
        (new Login()).visit();
        Element submit = new Element(browser, By.className("btn_action"));
        Assert.assertTrue(submit.doesExist());
    }

    @Test
    public void locatesTextField() {
        PageObject.setBrowser(browser);

        (new Login()).visit();
        TextField username = new TextField(browser, By.id("user-name"));

        Assert.assertTrue(username.doesExist());
    }

    @Test
    public void actsOnElements() {
        PageObject.setBrowser(browser);

        (new Login()).visit();
        TextField username = new TextField(browser, By.id("user-name"));
        username.sendKeys("standard_user");

        Assert.assertEquals("standard_user", username.getValue());
    }
}
