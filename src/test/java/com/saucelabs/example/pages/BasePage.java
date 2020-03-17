package com.saucelabs.example.pages;

import com.saucelabs.framework.pages.PageObject;
import lombok.Getter;

public class BasePage extends PageObject {
    @Getter private String baseURL = "https://address-book-example.herokuapp.com";
}
