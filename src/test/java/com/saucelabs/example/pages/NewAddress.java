package com.saucelabs.example.pages;

import com.saucelabs.example.data.Address;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

@OnPage(url="http://a.testaddressbook.com/addresses/new")
public class NewAddress extends PageObject {
    protected TextField firstName = new TextField(browser, By.id("address_first_name"));
    protected TextField lastName = new TextField(browser, By.id("address_last_name"));
    protected TextField streetAddress = new TextField(browser, By.id("address_street_address"));
    protected TextField secondaryAddress = new TextField(browser, By.id("address_secondary_address"));
    protected TextField city = new TextField(browser, By.id("address_city"));
    protected TextField zipCode = new TextField(browser, By.id("address_zip_code"));
    protected Element submit = new Element(browser, By.name("commit"));

    public void createAddress(Address address) {
        fillForm(address);
        submit.click();
    }
}
