# Sauce Framework

Scoping out what a more simple Java Test Framework might look like.
The name is provisional, but the intent is for this to be easy to use with Sauce Labs specifically.
We want something flexible that encourages good practices
The plan is to essentially create a light-watir implementation in Java, with the most powerful and easiest to implement
features, along with some of the best features of page-object gem

See LICENSE file for details.

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
1. Browser creation with `PlatformFactory` is not fully functional (it's barely enough to get the code to work)
2. Will get updated with Sauce Options support once beta is released
3. Is not currently working with Appium
4. Automatically relocates Stale Elements
5. Automatically waits for element to exist and be displayed
6. Automatically fills out forms when Data has same key names as Page Object element names
7. Thread Safe
8. No custom matchers present; Examples don't provide getters for elements


### Ideas
