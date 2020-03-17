package com.saucelabs.example.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saucelabs.framework.data.DataObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserData extends DataObject {
    @JsonIgnore
    private String id;
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
}
