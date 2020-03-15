package com.saucelabs.example.data;

import com.saucelabs.framework.data.DataObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address extends DataObject {
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String streetAddress = faker.address().streetAddress();
    private String secondaryAddress = faker.address().secondaryAddress();
    private String city = faker.address().city();
    private String zipCode = faker.address().zipCode();
}
