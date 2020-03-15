package com.saucelabs.example.data;

import com.saucelabs.framework.data.DataObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends DataObject {
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
}
