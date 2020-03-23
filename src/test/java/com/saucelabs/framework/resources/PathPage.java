package com.saucelabs.framework.resources;

import com.saucelabs.framework.pages.OnPage;
import com.saucelabs.framework.pages.PageObject;
import lombok.Getter;

@OnPage(path="inventory.html")
public class PathPage extends PageObject {
    @Getter private String baseURL = "https://www.saucedemo.com/";
}
