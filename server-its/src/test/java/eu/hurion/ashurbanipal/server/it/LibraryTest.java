package eu.hurion.ashurbanipal.server.it;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Nicolas Hurion
 */
public class LibraryTest extends BaseTest {

    @Test
    public void addANewBook() throws InterruptedException {
        driver.get(baseUrl + "/?restartApplication");
        driver.findElement(By.xpath("//input")).clear();
        driver.findElement(By.xpath("//input")).sendKeys("Children of Dune");
        driver.findElement(By.cssSelector("span.v-button-wrap")).click();

        waitForElement(By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[5]/td/div"), 3);

        assertTrue(isElementPresent(By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[5]/td/div")));
    }

    public void waitForElement(final By selector, final int secondsBeforeTimeOut) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= secondsBeforeTimeOut) {
                fail("timeout");
            }
            if (isElementPresent(selector)){
                break;
            }
            Thread.sleep(1000);
        }

    }

}
