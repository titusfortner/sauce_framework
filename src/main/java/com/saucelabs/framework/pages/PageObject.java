package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.exceptions.PageObjectException;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


public abstract class PageObject {
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    protected Browser browser = getBrowser();
    @Getter private String baseURL;
    OnPage required;

    public PageObject() {
        required = this.getClass().getAnnotation(OnPage.class);
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static void setBrowser(Browser browser) {
        browserThreadLocal.set(browser);
    }

    @SneakyThrows
    public void visit() {
        try {
            if (!required.url().isEmpty()) {
                browser.get(required.url());
                return;
            } else if (!required.path().isEmpty()) {
                browser.get(this.getBaseURL() + required.path());
                return;
            }
        } catch (NullPointerException ignored) {
        }
        throw new PageObjectException("url or path with baseURL must be defined to visit Page Object " + this.getClass());
    }

    @SneakyThrows
    public boolean isOnPage() {
        if (required == null) {
            throw new PageObjectException("No means were provided to check if on page for Page Object " + this.getClass());
        }
        if (!required.url().isEmpty() && !browser.getCurrentUrl().equals(required.url())) {
            return false;
        }
        if (!required.path().isEmpty() && !browser.getCurrentUrl().equals(this.getBaseURL() + required.path())) {
            return false;
        }
        return required.title().isEmpty() || browser.getTitle().equals(required.title());
    }
}
