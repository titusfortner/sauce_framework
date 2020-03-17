package com.saucelabs.example.apis;

import com.saucelabs.example.data.UserData;
import io.restassured.response.Response;
import lombok.Getter;

public class AuthenticationAPI extends BaseAPI {
    @Getter private String endpoint = "users";

    public UserData createUser() {
        return createUser(new UserData());
    }

    public UserData createUser(UserData userData) {
        Response response = create(userData);
        String rememberToken = response.getCookies().get("remember_token");

        // Post doesn't follow redirects, so can't auto convert to a user
        userData.setId(rememberToken);
        return userData;
    }
}
