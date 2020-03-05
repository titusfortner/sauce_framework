package com.saucelabs.framework.tests.pages;

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
        Assert.assertTrue(login.getSubmit().doesExist());
    }

}
