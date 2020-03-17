package com.saucelabs.example.tests.apis;

import com.saucelabs.example.apis.AddressAPI;
import com.saucelabs.example.apis.AuthenticationAPI;
import com.saucelabs.example.data.AddressData;
import com.saucelabs.example.data.UserData;
import com.saucelabs.example.pages.*;
import com.saucelabs.example.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AddressAPITest extends BaseTest {
    @Before
    public void authenticateUser() {
        AuthenticationAPI authenticationAPI = new AuthenticationAPI();
        UserData userData = authenticationAPI.createUser();

        HomePage homePage = new HomePage();
        homePage.visit();
        homePage.addCookie("remember_token", userData.getId());
    }

    @Test
    public void createsUIValidatesAPI() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();

        AddressData addressData = newAddressPage.createAddress();

        ShowAddressPage showAddressPage = new ShowAddressPage();
        String id = showAddressPage.getID();

        AddressAPI addressAPI = new AddressAPI();
        AddressData foundAddress = addressAPI.getAddress(id);

        Assert.assertEquals(foundAddress, addressData);
    }

    @Test
    public void createsAPIValidatesUI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        ShowAddressPage showAddressPage = new ShowAddressPage();
        showAddressPage.visit(addressData);

        Assert.assertTrue(showAddressPage.isAddress(addressData));
    }

    @Test
    public void createsAPIEditsUIValidatesAPI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        EditAddressPage editAddressPage = new EditAddressPage();
        editAddressPage.visit(addressData);

        AddressData editedAddressData = new AddressData();
        editAddressPage.editAddress(editedAddressData);

        AddressData foundAddress = addressAPI.getAddress(addressData.getId());

        Assert.assertEquals(foundAddress, editedAddressData);
    }

    // Don't usually need to list in API for UI tests
    @Test
    public void createsUIListsAPI() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();
        AddressData addressData1 = newAddressPage.createAddress();

        NewAddressPage newAddressPage2 = new NewAddressPage();
        newAddressPage2.visit();
        AddressData addressData2 = newAddressPage2.createAddress();

        List<AddressData> createdAddresses = new ArrayList<>();
        createdAddresses.add(addressData1);
        createdAddresses.add(addressData2);

        AddressAPI addressAPI = new AddressAPI();
        List<AddressData> addresses = addressAPI.getAddresses();
        Assert.assertEquals(createdAddresses, addresses);
    }

    @Test
    public void createsAPIDeletesUIValidatesAPI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        ListAddressPage listAddressPage = new ListAddressPage();
        listAddressPage.visit();
        listAddressPage.delete(addressData.getId());

        AddressData foundAddress = addressAPI.getAddress(addressData.getId());

        Assert.assertNull(foundAddress);
    }
}
