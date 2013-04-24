package eu.hurion.ashurbanipal.server.it;

import cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author Nicolas Hurion
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber-html-report"})
public class RunCukesTest {
    private static ServerHooks serverHooks = new ServerHooks();

    @BeforeClass
    public static void startup(){
        serverHooks.startup();
    }

    @AfterClass
    public static void stop() throws InterruptedException {
        serverHooks.shutdown();
    }
}
