package com.saucelabs.framework.resources;

import com.saucelabs.framework.data.DataObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserData extends DataObject {
    private String username = faker.name().username();
    private String password = faker.internet().password();

    public static UserData valid() {
        UserData userData = new UserData();
        userData.username = "standard_user";
        userData.password = "secret_sauce";
        return userData;
    }
}
