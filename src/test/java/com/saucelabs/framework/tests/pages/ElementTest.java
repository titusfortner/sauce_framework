package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.elements.HTMLElement;
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

        Login login = new Login();
        Assert.assertEquals(hashSet, login.getElements());
    }

    @Test
    public void getsValue() {
        Login login = new Login();
        HTMLElement username = new TextField(browser, By.id("username"));

        HTMLElement value = (HTMLElement) login.getValue("username");
        Assert.assertEquals(username, value);
    }

    @Test
    public void locatesElements() {
        PageObject.setBrowser(browser);

        Login login = Login.visit();
        Assert.assertEquals(HTMLElement.class, login.getSubmit().getClass());
        Assert.assertTrue(login.getSubmit().doesExist());
    }

    @Test
    public void locatesTextField() {
        PageObject.setBrowser(browser);

        Login login = Login.visit();
        Assert.assertEquals(TextField.class, login.getUsername().getClass());
        Assert.assertTrue(login.getUsername().doesExist());
    }

    @Test
    public void actsOnElements() {
        PageObject.setBrowser(browser);

        Login login = Login.visit();
        login.getUsername().sendKeys("standard_user");

        Assert.assertEquals("standard_user", login.getUsername().getValue());
    }
}
