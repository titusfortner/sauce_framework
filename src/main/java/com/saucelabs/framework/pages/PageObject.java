package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import lombok.Setter;


public abstract class PageObject {
    @Setter public static Browser browser;
    @Getter public String url;

    public boolean isOnPage() {
        return browser.getCurrentUrl().equals(getUrl());
    }
}
