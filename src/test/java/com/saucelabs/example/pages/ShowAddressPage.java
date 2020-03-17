package com.saucelabs.example.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.elements.Element;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAddressPage extends BasePage {

    private Element notice = browser.element(By.cssSelector("[data-test=notice]"));
    private Element firstName = browser.element(By.cssSelector("[data-test=first_name]"));
    private Element lastName = browser.element(By.cssSelector("[data-test=last_name]"));
    private Element streetAddress = browser.element(By.cssSelector("[data-test=street_address]"));
    private Element secondaryAddress = browser.element(By.cssSelector("[data-test=secondary_address]"));
    private Element city = browser.element(By.cssSelector("[data-test=city]"));
    private Element zipCode = browser.element(By.cssSelector("[data-test=zip_code]"));

    public boolean hasSuccessMessage() {
        return isOnPage() && notice.isVisible();
    }

    public boolean isAddress(AddressData address) {
        return isCorrectData(address);
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
