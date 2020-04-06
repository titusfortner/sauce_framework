# Sauce Framework

Scoping out what a more simple Java Test Framework might look like.
The name is provisional, but the intent is for this to be easy to use with Sauce Labs specifically.
We want something flexible that encourages good practices
The plan is to essentially create a light-watir implementation in Java, with the most powerful and easiest to implement
features, along with some of the best features of page-object gem

Inspiration for this code drawn from numerous places including:
* [Watir](https://watir.com)
* [Mineraloil](https://github.com/lithiumtech/mineraloil-selenium.git)


See LICENSE file for details.

### Usage

This is not currently released on Maven. For now you can use it locally by running:
```
mvn package
mvn install
```


### Goals

1. Easy to toggle Local & Remote
2. Easy to use with Sauce Bindings
3. Support for Desktop & Mobile
4. Allow automatic relocation of Stale Elements
5. Smart Synchronization Strategy
6. Simple Form Filling
7. Test Runner agnostic (Thread Safe!!)
8. Discourage imperative test writing


### Status
1. Implementation for generating Browser Options
2. Basic browser wrapper
3. Basic element wrapper
4. Synchronization implementation for element methods
5. Staleness Relocation
6. Collection Support
7. Page Object Implementation
8. Data Object Implementation
9. Nested Elements Supported
10. Execute Script
11. Actions Class Wrapper (Scroll, Hover, Right Click, Double Click, Drag & Drop)


### Future Ideas
1. Direct Support for IFrames
2. Subclass Elements
3. Automatic Form Filling
4. Automatic Data Reading
5. Logging Support
6. API Integration
7. Alert Wrapper
8. Windows Wrapper
9. Screenshot Wrapper
10. Cookies Wrapper
11. Scrolling Support
12. After Hooks


### Example Code

 The test directory has a number of examples to demonstrate usage, but basically the syntax looks like:
 
 ```java
Browser browser = new Browser();
PageObject.setBrowser(browser);
browser.goTo("https://www.saucedemo.com/");

userName userName = browser.element(By.id("user-name"));
userName.setText("standard_user");
Element password = browser.element(By.id("password"));
password.setText("secret_sauce");
Element submitButton = browser.element(By.className("btn_action"));
submitButton.click();

Assert.assertEquals("https://www.saucedemo.com/inventory.html", browser.getCurrentUrl())

browser.quit();
```

There are also a basic `DataObject` and `PageObject` classes that will allow you to
