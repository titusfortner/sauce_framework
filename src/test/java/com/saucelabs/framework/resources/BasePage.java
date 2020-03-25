package com.saucelabs.framework.resources;

import com.saucelabs.framework.pages.PageObject;
import lombok.Getter;

public class BasePage extends PageObject {
    @Getter private String baseURL = "https://www.saucedemo.com";
}
