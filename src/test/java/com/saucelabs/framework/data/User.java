package com.saucelabs.framework.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends DataObject {
    private String username = faker.name().username();
    private String password = faker.internet().password();
    private Boolean submit = true;

    public static User valid() {
        User user = new User();
        user.username = "standard_user";
        user.password = "secret_sauce";
        return user;
    }

}
