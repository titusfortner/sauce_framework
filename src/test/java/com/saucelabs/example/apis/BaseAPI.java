package com.saucelabs.example.apis;

import com.saucelabs.framework.apis.APIObject;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public abstract class BaseAPI extends APIObject {
    @Getter private String baseURL = "https://address-book-example.herokuapp.com/";

    protected RequestSpecification generateGiven() {
        return given();
    }
}
