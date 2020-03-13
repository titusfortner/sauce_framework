package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.elements.HTMLElement;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            String getter = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = this.getClass().getDeclaredMethod(getter);
            return method.invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOnPage() {
        return browser.getCurrentUrl().equals(getUrl());
    }
}
