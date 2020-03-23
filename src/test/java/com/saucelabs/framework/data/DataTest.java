package com.saucelabs.framework.data;

import com.saucelabs.framework.resources.UserData;
import org.junit.Assert;
import org.junit.Test;

public class DataTest {
    @Test
    public void createsNewDataObject() {
        UserData userData = new UserData();

        Assert.assertEquals(String.class, userData.getUsername().getClass());
        Assert.assertEquals(String.class, userData.getPassword().getClass());
    }

    @Test
    public void createsStaticDataObject() {
        UserData userData = UserData.valid();

        Assert.assertEquals("standard_user", userData.getUsername());
        Assert.assertEquals("secret_sauce", userData.getPassword());
    }
}
