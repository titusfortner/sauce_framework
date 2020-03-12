package com.saucelabs.framework.tests.data;

import com.saucelabs.framework.data.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;


public class DataTest {
    @Test
    public void createsNewDataObject() {
        User user = new User();

        Assert.assertEquals(String.class, user.getUsername().getClass());
        Assert.assertEquals(String.class, user.getPassword().getClass());
    }

    @Test
    public void createsStaticDataObject() {
        User user = User.valid();

        Assert.assertEquals("standard_user", user.getUsername());
        Assert.assertEquals("secret_sauce", user.getPassword());
    }

    @Test
    public void createsKeySet() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("username");
        hashSet.add("password");

        User user = new User();
        Assert.assertEquals(hashSet, user.getKeys());
    }

    @Test
    public void getsValue() {
        User user = new User();
        user.setUsername("foo");

        String value = (String) user.getValue("username");
        Assert.assertEquals("foo", value);
    }
}
