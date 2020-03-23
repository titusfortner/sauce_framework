package com.saucelabs.framework.data;

import com.saucelabs.framework.resources.UserData;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class DataTest {
    @Test
    public void createsNewDataObjectWithDefaults() {
        UserData userData = new UserData();

        Assert.assertNotNull(userData.getUsername());
        Assert.assertEquals(String.class, userData.getUsername().getClass());
        Assert.assertNotNull(userData.getPassword());
        Assert.assertEquals(String.class, userData.getPassword().getClass());
    }

    @Test
    public void updatesDataObject() {
        UserData userData = new UserData();
        userData.setUsername("standard_user");
        userData.setPassword("secret_sauce");

        Assert.assertEquals("standard_user", userData.getUsername());
        Assert.assertEquals("secret_sauce", userData.getPassword());
    }

    @Test
    public void createsStaticDefinedDataObject() {
        UserData userData = UserData.valid();

        Assert.assertEquals("standard_user", userData.getUsername());
        Assert.assertEquals("secret_sauce", userData.getPassword());
    }

    @Test
    public void createsKeySet() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("username");
        hashSet.add("password");

        UserData userData = new UserData();
        Assert.assertEquals(hashSet, userData.getKeys());
    }

    @Test
    public void getsValueMethod() {
        UserData userData = new UserData();
        userData.setUsername("foo");

        String value = (String) userData.getValue("username");
        Assert.assertEquals("foo", value);
    }
}
