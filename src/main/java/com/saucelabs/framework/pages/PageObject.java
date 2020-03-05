package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.elements.HTMLElement;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;


public abstract class PageObject {
    @Setter public static Browser browser;
    @Getter public String url;

    public HTMLElement element(Callable<HTMLElement> block) {
        try {
            return block.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOnPage() {
        return browser.getCurrentUrl().equals(getUrl());
    }
}
