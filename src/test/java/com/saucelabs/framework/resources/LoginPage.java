package com.saucelabs.framework.resources;

import com.saucelabs.framework.pages.PageObject;
import lombok.Getter;


public class LoginPage extends PageObject {
    @Getter public String url = "https://www.saucedemo.com/";
}
