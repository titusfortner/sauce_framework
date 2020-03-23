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
2. Page Object Implementation
3. Data Object Implementation

### Future Ideas
1. Support Nested Elements
2. Subclass Elements
3. Automatic Form Filling
4. Automatic Data Reading
5. Execute Scripts
6. Logging Support
7. API Integration
8. Common Actions Class Wrapper: Hover, Drag & Drop, etc
10. Alert Wrapper
11. Windows Wrapper
12. Screenshot Wrapper
13. Cookies Wrapper
14. Scrolling Support
15. After Hooks
