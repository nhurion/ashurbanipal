package eu.hurion.ashurbanipal.server.it;


import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.runtime.ScenarioResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

/**
 * Based on example found in https://github.com/cucumber/cucumber-jvm/tree/master/examples/java-webbit-websockets-selenium
 *
 * Example of a WebDriver implementation that has an underlying instance that is used for all scenarios and closed
 * when the JVM exits. This saves time. To prevent browser state from leaking between scenarios, cookies are deleted before
 * every scenario.
 * <p/>
 * As a bonus, screenshots are embedded into the report for each scenario. (This only works
 * if you're also using the HTML formatter).
 * <p/>
 * This class can be shared across step definitions via dependency injection.
 */
public class SharedDriver extends EventFiringWebDriver {
    private static final Logger LOG = LoggerFactory.getLogger(SharedDriver.class);
    private static final WebDriver REAL_DRIVER = new FirefoxDriver();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                REAL_DRIVER.close();
            }
        });
    }

    public SharedDriver() {
        super(REAL_DRIVER);
        REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(final ScenarioResult result) {
        try {
            final byte[] screenshot = this.getScreenshotAs(OutputType.BYTES);
            result.embed(new ByteArrayInputStream(screenshot), "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            LOG.error(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
}