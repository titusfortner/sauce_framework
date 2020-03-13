package com.saucelabs.framework.data;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public abstract class DataObject {
    static Faker faker = new Faker();
    @Getter private Set<String> keys = new HashSet<>();

    public DataObject() {
        for (Field field : this.getClass().getDeclaredFields()) {
            keys.add(field.getName());
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
}
