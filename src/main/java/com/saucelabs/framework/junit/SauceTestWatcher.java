package com.saucelabs.framework.junit;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import lombok.Setter;
import org.junit.runner.Description;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceTestWatcher extends BaseTestWatcher {
    @Setter private SauceSession sauceSession;
    @Setter private SauceOptions sauceOptions;

    protected void starting(Description description) {
        sauceOptions = getSauceOptions();
        sauceOptions.setName(description.getMethodName());

        if (System.getProperty("BUILD_NAME") != null) {
            sauceOptions.setBuild(System.getProperty("BUILD_NAME"));
        }

        sauceSession = getSauceSession();
        sauceSession.start();
    }

    private SauceOptions getSauceOptions() {
        if (sauceOptions == null) {
            sauceOptions = new SauceOptions();
        }
        return sauceOptions;
    }

    private SauceSession getSauceSession() {
        if (sauceSession == null) {
            sauceSession = new SauceSession(sauceOptions);
        }
        return sauceSession;
    }

    public RemoteWebDriver getDriver() {
        return sauceSession.getDriver();
    }

    protected void succeeded(Description description) {
        sauceSession.stop("passed");
    }

    protected void failed(Description description) {
        sauceSession.stop("failed");
    }
}
