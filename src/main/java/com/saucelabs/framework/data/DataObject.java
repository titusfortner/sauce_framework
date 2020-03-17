package com.saucelabs.framework.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value={"keys", "id"}, ignoreUnknown = true)
public abstract class DataObject {
    public static Faker faker = new Faker();
    @Getter private Set<String> keys = new HashSet<>();
    @Getter @Setter private String id;

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

    @SneakyThrows
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!this.getClass().isInstance(o)) {
            return false;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this).equals(mapper.writeValueAsString(o));
        }
    }
}
