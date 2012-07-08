package eu.hurion.ashurbanipal.server.it;

import com.bsb.common.vaadin.embed.EmbedVaadinServer;
import eu.hurion.ashurbanipal.server.Launcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Nicolas Hurion
 */
public abstract class BaseTest {

    private static EmbedVaadinServer application;
    protected FirefoxDriver driver;
    protected String baseUrl;

    @BeforeClass
    public static void startSever(){
        Launcher.setDevMode(true);
        application = Launcher.buildServer().wait(false).build();
        application.start();
    }

    @AfterClass
    public static void endServer(){
        application.stop();
    }

    @Before
    public void prepareDriver(){
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);    }

    @After
    public void closeDriver(){
        driver.close();
    }



    protected boolean isElementPresent(final By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}


