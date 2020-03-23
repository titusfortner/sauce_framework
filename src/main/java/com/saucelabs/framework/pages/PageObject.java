package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.exceptions.PageObjectException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public abstract class PageObject {
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    protected Browser browser = getBrowser();
    @Getter private String baseURL;
    @Getter private Set<String> elements = new HashSet<>();
    OnPage required;

    public PageObject() {
        required = this.getClass().getAnnotation(OnPage.class);
        for (Field field : this.getClass().getDeclaredFields()) {
            if (Element.class.isAssignableFrom(field.getType())) {
                elements.add(field.getName());
            }
        }
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
                browser.goTo(required.url());
                return;
            } else if (!required.path().isEmpty()) {
                browser.goTo(this.getBaseURL() + required.path());
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
        if (!required.title().isEmpty() && !browser.getTitle().equals(required.title())) {
            return false;
        }
        for (String element : required.elements()) {
            Element htmlElement = (Element) getElement(element);
            if (!htmlElement.doesExist()) {
                return false;
            }
        }
        return true;
    }

    public Object getElement(String key) {
        try {
            Field declaredField = this.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            return declaredField.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
