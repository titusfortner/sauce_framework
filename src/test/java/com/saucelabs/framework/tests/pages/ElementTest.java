package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.Login;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ElementTest extends BaseTest {
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
        Assert.assertEquals(TextField.class, login.getUserName().getClass());
        Assert.assertTrue(login.getUserName().doesExist());
    }

    @Test
    public void actsOnElements() {
        PageObject.setBrowser(browser);

        Login login = Login.visit();
        login.getUserName().sendKeys("standard_user");

        Assert.assertEquals("standard_user", login.getUserName().getValue());
    }

    @Test
    public void methodActsOnElements() {
        PageObject.setBrowser(browser);

        Login login = Login.visit();
        login.login("standard_user", "secret_sauce");

        Assert.assertFalse(login.isOnPage());
    }
}
