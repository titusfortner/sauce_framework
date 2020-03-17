package com.saucelabs.example.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saucelabs.framework.data.DataObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressData extends DataObject {
    @JsonIgnore
    private String id;
    @JsonProperty("first_name")
    private String firstName = faker.name().firstName();
    @JsonProperty("last_name")
    private String lastName = faker.name().lastName();
    @JsonProperty("address1")
    private String streetAddress = faker.address().streetAddress();
    @JsonProperty("address2")
    private String secondaryAddress = faker.address().secondaryAddress();
    private String city = faker.address().city();
    private String state = "AL";
    @JsonProperty("zip_code")
    private String zipCode = faker.address().zipCode();
}
