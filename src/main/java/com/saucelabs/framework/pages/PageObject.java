package com.saucelabs.framework.pages;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.data.DataObject;
import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.elements.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.openqa.selenium.Cookie;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public abstract class PageObject {
    @Getter private String baseURL;
    @Getter @Setter protected static Browser browser;
    @Getter private Set<String> elements = new HashSet<>();
    OnPage required;

    public PageObject() {
        required = (OnPage) this.getClass().getAnnotation(OnPage.class);
        for (Field field : this.getClass().getDeclaredFields()) {
            if (Element.class.isAssignableFrom(field.getType())) {
                elements.add(field.getName());
            }
        }
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
        throw new Exception("Either path or url must be defined in Page Object " + this.getClass());
    }

    @SneakyThrows
    public boolean isOnPage() {
        if (required == null) {
            throw new Exception("No means were provided to check if on page for Page Object " + this.getClass());
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

    public void fillForm(DataObject data) {
        Set<String> keys = data.getKeys();

        for (String key : elements) {
            if (keys.contains(key)) {
                setValue((Element) getElement(key), data.getValue(key));
            }
        }
    }

    public boolean isCorrectData(DataObject data) {
        Set<String> keys = data.getKeys();

        for (String key : elements) {
            if (keys.contains(key) && !valueMatches((Element) getElement(key), data.getValue(key))) {
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

    public void setValue(Element el, Object value) {
        if (el.getClass().equals(TextField.class)) {
            ((TextField) el).set((String) value);
        }
    }

    public boolean valueMatches(Element el, Object value) {
        return el.getText().equals(value);
    }

    public void addCookie(Cookie cookie) {
        getBrowser().manage().addCookie(cookie);
    }

    public void addCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        addCookie(cookie);
    }
}
