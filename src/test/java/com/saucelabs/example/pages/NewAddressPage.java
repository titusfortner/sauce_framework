package com.saucelabs.example.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;

@OnPage(path="/addresses/new")
public class NewAddressPage extends BasePage {

    private TextField firstName = browser.textField(By.id("address_first_name"));
    private TextField lastName = browser.textField(By.id("address_last_name"));
    private TextField streetAddress = browser.textField(By.id("address_street_address"));
    private TextField secondaryAddress = browser.textField(By.id("address_secondary_address"));
    private TextField city = browser.textField(By.id("address_city"));
    private TextField zipCode = browser.textField(By.id("address_zip_code"));
    private Element submit = browser.element(By.name("commit"));

    public AddressData createAddress() {
        return createAddress(new AddressData());
    }

    public AddressData createAddress(AddressData address) {
        fillForm(address);
        submit.click();
        return address;
    }
}







