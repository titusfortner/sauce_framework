package com.saucelabs.example.tests;

import com.saucelabs.example.data.Address;
import com.saucelabs.example.pages.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressTest extends BaseTest {
    @Before
    public void signUp() {
        SignUp signUp = new SignUp();
        signUp.visit();
        signUp.signUp();
    }

    @Test
    public void creates() {
        NewAddress newAddress = new NewAddress();
        newAddress.visit();

        Address address = new Address();
        newAddress.createAddress(address);

        ShowAddress showAddress = new ShowAddress();

        Assert.assertTrue(showAddress.hasSuccessMessage());
    }

    @Test
    public void shows() {
        NewAddress newAddress = new NewAddress();
        newAddress.visit();
        Address address = new Address();
        newAddress.createAddress(address);

        ShowAddress showAddress = new ShowAddress();

        Assert.assertTrue(showAddress.isAddress(address));
    }

    @Test
    public void lists() {
        NewAddress newAddress = new NewAddress();
        newAddress.visit();
        Address address = new Address();
        newAddress.createAddress(address);
        ShowAddress showAddress = new ShowAddress();
        String id = showAddress.getID();
        ListAddress listAddress = new ListAddress();
        listAddress.visit();

        Assert.assertTrue(listAddress.hasAddress(id));
    }

    @Test
    public void edits() {
        NewAddress newAddress = new NewAddress();
        newAddress.visit();
        Address address = new Address();
        newAddress.createAddress(address);
        ShowAddress showAddress = new ShowAddress();
        String id = showAddress.getID();

        EditAddress editAddress = new EditAddress();
        editAddress.visit(id);

        Address editedAddress = new Address();
        editAddress.editAddress(editedAddress);

        Assert.assertTrue(showAddress.hasSuccessMessage());
        Assert.assertTrue(showAddress.isAddress(editedAddress));
    }

    @Test
    public void deletes() {
        NewAddress newAddress = new NewAddress();
        newAddress.visit();
        Address address = new Address();
        newAddress.createAddress(address);
        ShowAddress showAddress = new ShowAddress();
        String id = showAddress.getID();

        ListAddress listAddress = new ListAddress();
        listAddress.visit();

        listAddress.delete(id);

        Assert.assertTrue(listAddress.hasSuccessMessage());
        Assert.assertFalse(listAddress.hasAddress(id));
    }
}
