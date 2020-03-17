package com.saucelabs.example.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;

@OnPage(path="/addresses/new")
public class NewAddressPage extends BasePage {

    protected TextField firstName = new TextField(browser, By.id("address_first_name"));
    protected TextField lastName = new TextField(browser, By.id("address_last_name"));
    protected TextField streetAddress = new TextField(browser, By.id("address_street_address"));
    protected TextField secondaryAddress = new TextField(browser, By.id("address_secondary_address"));
    protected TextField city = new TextField(browser, By.id("address_city"));
    protected TextField zipCode = new TextField(browser, By.id("address_zip_code"));
    protected Element submit = new Element(browser, By.name("commit"));

    public AddressData createAddress() {
        return createAddress(new AddressData());
    }

    public AddressData createAddress(AddressData address) {
        fillForm(address);
        submit.click();
        return address;
    }
}
