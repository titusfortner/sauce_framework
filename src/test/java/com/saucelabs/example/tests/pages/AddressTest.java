package com.saucelabs.example.tests.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.example.pages.*;
import com.saucelabs.example.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressTest extends BaseTest {
    @Before
    public void signUp() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.visit();
        signUpPage.signUp();
    }

    @Test
    public void creates() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();

        AddressData address = new AddressData();
        newAddressPage.createAddress(address);

        ShowAddressPage showAddressPage = new ShowAddressPage();

        Assert.assertTrue(showAddressPage.hasSuccessMessage());
    }

    @Test
    public void shows() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();
        AddressData address = new AddressData();
        newAddressPage.createAddress(address);

        ShowAddressPage showAddressPage = new ShowAddressPage();

        Assert.assertTrue(showAddressPage.isAddress(address));
    }

    @Test
    public void lists() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();
        AddressData address = new AddressData();
        newAddressPage.createAddress(address);
        ShowAddressPage showAddressPage = new ShowAddressPage();
        String id = showAddressPage.getID();
        ListAddressPage listAddressPage = new ListAddressPage();
        listAddressPage.visit();

        Assert.assertTrue(listAddressPage.hasAddress(id));
    }

    @Test
    public void edits() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();
        AddressData address = new AddressData();
        newAddressPage.createAddress(address);
        ShowAddressPage showAddressPage = new ShowAddressPage();
        String id = showAddressPage.getID();

        EditAddressPage editAddress = new EditAddressPage();
        editAddress.visit(id);

        AddressData editedAddress = new AddressData();
        editAddress.editAddress(editedAddress);

        Assert.assertTrue(showAddressPage.hasSuccessMessage());
        Assert.assertTrue(showAddressPage.isAddress(editedAddress));
    }

    @Test
    public void deletes() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();
        AddressData address = new AddressData();
        newAddressPage.createAddress(address);
        ShowAddressPage showAddressPage = new ShowAddressPage();
        String id = showAddressPage.getID();

        ListAddressPage listAddressPage = new ListAddressPage();
        listAddressPage.visit();

        listAddressPage.delete(id);

        Assert.assertTrue(listAddressPage.hasSuccessMessage());
        Assert.assertFalse(listAddressPage.hasAddress(id));
    }
}
