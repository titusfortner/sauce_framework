package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.data.User;
import com.saucelabs.framework.pages.Login;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class DataTest extends BaseTest {
    @Test
    public void methodActsOnElements() {
        PageObject.setBrowser(browser);

        Login login = new Login();
        login.visit();
        login.login(User.valid());

        Assert.assertFalse(login.isOnPage());
    }
}
