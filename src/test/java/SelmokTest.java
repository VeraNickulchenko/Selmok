import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import selmok.Selmok;
import selmok.datagenerator.DataGenerator;
import selmok.webdriver_wrapper.Browser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class SelmokTest {
    private static final String TEST_URL = "test_url";
    private static final String TEST_LOCATOR = "someLocator";


    @Test
    public void seleniumInitTest(){

        WebDriver driver = new ChromeDriver();

/**        Just to generate the String, which will only contains the lowercase letters.
 *         to add even more customization a huge method required to be implemented.
 */
        StringBuilder randomString = new StringBuilder(10);
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 10; i++) {
            randomString.append(CHARACTERS.charAt(new Random().nextInt(CHARACTERS.length())));
        }

/**     Selenium code to open the browser, get to specific url and make interactions with
 *      WebElements */
        driver.manage().window().maximize();
        driver.get(TEST_URL);

        WebElement el = driver.findElement(By.xpath(TEST_LOCATOR));
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOf(el));

        el.sendKeys(randomString.toString());

        action.moveToElement(el)
                .click(el)
                .build()
                .perform();


    }

    @Test
    public void selmokTest(){

        Selmok selmok = Selmok.compiler()
                .browser(new Browser(new ChromeDriver()))
                .generator(new DataGenerator())
                .compile();

        selmok.browser().browserDriver().manage().window().maximize();
        WebElement el = selmok.browser().browserDriver().findElement(By.xpath(TEST_LOCATOR));
        selmok.browser().uiAction().putMouseOverAndClickOnElement(el, Duration.of(10, ChronoUnit.SECONDS));
        selmok.browser().uiAction().inputValueToElement(el,
                selmok.generator().random().randomString(10, true, true, false));



    }
}
