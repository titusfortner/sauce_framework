package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.data.DataObject;
import com.saucelabs.framework.elements.HTMLElement;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public abstract class PageObject {
    @Setter public static Browser browser;
    @Getter public String url;
    @Getter private Set<String> elements = new HashSet<>();

    public PageObject() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (HTMLElement.class.isAssignableFrom(field.getType())) {
                elements.add(field.getName());
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

    public boolean isOnPage() {
        return browser.getCurrentUrl().equals(getUrl());
    }

    public void fillForm(DataObject data) {
        Set<String> keys = data.getKeys();

        for (String key : elements) {
            if (keys.contains(key)) {
                setValue((HTMLElement) getValue(key), data.getValue(key));
            }
        }
    }

    public void setValue(HTMLElement el, Object value) {
        if (el.getClass().equals(TextField.class)) {
            ((TextField) el).set((String) value);
        }
    }
}
