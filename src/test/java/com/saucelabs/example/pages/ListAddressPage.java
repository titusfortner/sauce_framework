package com.saucelabs.example.pages;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import org.openqa.selenium.By;

@OnPage(url="https://address-book-example.herokuapp.com/addresses")
public class ListAddressPage extends PageObject {

    protected Element notice = new Element(browser, By.cssSelector("[data-test=notice]"));

    public boolean hasAddress(AddressData addressData) {
        return hasAddress(addressData.getId());
    }

    public boolean hasAddress(String id) {
        Element showID = new Element(browser, By.cssSelector("[data-test=show-" + id + "]"));
        return showID.doesExist();
    }

    public void delete(String id) {
        Element destroyID = new Element(browser, By.cssSelector("[data-test=destroy-" + id + "]"));
        destroyID.click();
        browser.switchTo().alert().accept();
    }

    public boolean hasSuccessMessage() {
        return isOnPage() && notice.isVisible();
    }
}
