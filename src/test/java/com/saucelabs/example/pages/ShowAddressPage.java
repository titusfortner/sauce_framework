package com.saucelabs.example.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAddressPage extends PageObject {

    protected Element notice = new Element(browser, By.cssSelector("[data-test=notice]"));
    protected Element firstName = new Element(browser, By.cssSelector("[data-test=first_name]"));
    protected Element lastName = new Element(browser, By.cssSelector("[data-test=last_name]"));
    protected Element streetAddress = new Element(browser, By.cssSelector("[data-test=street_address]"));
    protected Element secondaryAddress = new Element(browser, By.cssSelector("[data-test=secondary_address]"));
    protected Element city = new Element(browser, By.cssSelector("[data-test=city]"));
    protected Element zipCode = new Element(browser, By.cssSelector("[data-test=zip_code]"));

    public boolean hasSuccessMessage() {
        return isOnPage() && notice.isVisible();
    }

    public boolean isAddress(AddressData address) {
        return address.getFirstName().equals(firstName.getText()) &&
                address.getLastName().equals(lastName.getText()) &&
                address.getStreetAddress().equals(streetAddress.getText()) &&
                address.getSecondaryAddress().equals(secondaryAddress.getText()) &&
                address.getCity().equals(city.getText()) &&
                address.getZipCode().equals(zipCode.getText());
    }

    @Override
    public boolean isOnPage() {
        return browser.getCurrentUrl().matches("^.*\\d$");
    }

    public String getID() {
        String url = browser.getCurrentUrl();

        Matcher matcher = Pattern.compile("(\\d+)$").matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public void visit(String id) {
        browser.get("https://address-book-example.herokuapp.com/addresses/" + id);
    }

    public void visit(AddressData addressData) {
        visit(addressData.getId());
    }
}
