package com.saucelabs.framework.tests.pages;

import com.saucelabs.framework.pages.Login;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.framework.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class NavigationTest extends BaseTest {
    @Test
    public void navigates() {
        PageObject.setBrowser(browser);

        Login login = new Login();
        login.visit();
        Assert.assertTrue(login.isOnPage());
    }

}
