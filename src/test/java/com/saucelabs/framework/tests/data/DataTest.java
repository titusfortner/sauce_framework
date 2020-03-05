package com.saucelabs.framework.tests.data;

import com.saucelabs.framework.data.User;
import org.junit.Assert;
import org.junit.Test;

public class DataTest {
    @Test
    public void createsNewDataObject() {
        User user = new User();

        Assert.assertEquals(String.class, user.getUsername().getClass());
        Assert.assertEquals(String.class, user.getPassword().getClass());
        Assert.assertEquals(Boolean.class, user.getSubmit().getClass());
    }

    @Test
    public void createsStaticDataObject() {
        User user = User.valid();

        Assert.assertEquals("standard_user", user.getUsername());
        Assert.assertEquals("secret_sauce", user.getPassword());
        Assert.assertTrue(user.getSubmit());
    }
}
