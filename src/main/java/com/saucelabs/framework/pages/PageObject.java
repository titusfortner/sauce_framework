package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.data.DataObject;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public abstract class PageObject {
    @Setter public static Browser browser;
    @Getter private Set<String> elements = new HashSet<>();
    OnPage required;

    public PageObject() {
        required = (OnPage) this.getClass().getAnnotation(OnPage.class);
        for (Field field : this.getClass().getDeclaredFields()) {
            if (HTMLElement.class.isAssignableFrom(field.getType())) {
                elements.add(field.getName());
            }
        }
    }

    @SneakyThrows
    public void visit() {
        try {
            if (!required.url().isEmpty()) {
                browser.get(required.url());
            }
        } catch (NullPointerException ex) {
            throw new Exception("Unable to visit as URL is not defined in Page Object " + this.getClass());
        }
    }

    @SneakyThrows
    public boolean isOnPage() {
        if (required == null) {
            throw new Exception("No means were provided to check if on page forPage Object " + this.getClass());
        }
        if (!required.url().isEmpty() && !browser.getCurrentUrl().equals(required.url())) {
            return false;
        }
        if (!required.title().isEmpty() && !browser.getTitle().equals(required.title())) {
            return false;
        }
        for (String element : required.elements()) {
            HTMLElement htmlElement = (HTMLElement) getValue(element);
            if (!htmlElement.doesExist()) {
                return false;
            }
        }
        return true;
    }

    public void fillForm(DataObject data) {
        Set<String> keys = data.getKeys();

        for (String key : elements) {
            if (keys.contains(key)) {
                setValue((HTMLElement) getValue(key), data.getValue(key));
            }
        }
    }

    public Object getValue(String key) {
        try {
            return this.getClass().getDeclaredField(key).get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setValue(HTMLElement el, Object value) {
        if (el.getClass().equals(TextField.class)) {
            ((TextField) el).set((String) value);
        }
    }
}
