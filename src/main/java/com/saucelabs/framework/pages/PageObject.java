package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


public abstract class PageObject {
    @Setter public static Browser browser;
    @Getter private String baseURL;
    OnPage required;

    public PageObject() {
        required = this.getClass().getAnnotation(OnPage.class);
    }

    @SneakyThrows
    public void visit() {
        try {
            if (!required.url().isEmpty()) {
                browser.get(required.url());
                return;
            }
        } catch (NullPointerException ignored) {
        }
        throw new Exception("URL must be defined in Page Object " + this.getClass());
    }

    @SneakyThrows
    public boolean isOnPage() {
        if (required == null) {
            throw new Exception("No means were provided to check if on page for Page Object " + this.getClass());
        }
        if (!required.url().isEmpty() && !browser.getCurrentUrl().equals(required.url())) {
            return false;
        }
        return required.title().isEmpty() || browser.getTitle().equals(required.title());
    }
}
