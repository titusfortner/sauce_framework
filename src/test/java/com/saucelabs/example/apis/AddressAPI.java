package com.saucelabs.example.apis;

import com.saucelabs.example.data.AddressData;
import com.saucelabs.framework.pages.PageObject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddressAPI extends BaseAPI {
    @Getter private String endpoint = "addresses";

    @Override
    protected RequestSpecification generateGiven() {
        String rememberToken = PageObject.browser.manage().getCookieNamed("remember_token").getValue();

        return given().header("Cookie", "remember_token=" + rememberToken);
    }

    public AddressData createAddress() {
        return createAddress(new AddressData());
    }

    public AddressData createAddress(AddressData address) {
        Response response = create(address);
        String id = response.then().extract().path("id").toString();
        AddressData actualAddress = response.as(AddressData.class);
        actualAddress.setId(id);
        return actualAddress;
    }

    public AddressData getAddress(String id) {
        Response response = read(id);
        if (response.getStatusCode() == 404) {
            return null;
        }
        return response.as(AddressData.class);
    }

    public List<AddressData> getAddresses() {
        Response response = list();
        return response.as(new TypeRef<>() {});
    }
}
