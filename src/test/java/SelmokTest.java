import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import selmok.Selmok;
import selmok.datagenerator.Generator;
import selmok.browser.Browser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class SelmokTest {
    private static final String TEST_URL = "https://test_url";
    private static final String INPUT_ELEMENT_TEST_LOCATOR = "//input";
    private static final String  CLICK_ELEMENT_TEST_LOCATOR = "//button";



    @Test
    public void seleniumInitTest(){

        WebDriver driver = new ChromeDriver();

/**        Приклад генерації випадкової строки,
 *          яка міститиме тільки маленькі літери.
 *         Для того, щоб додати більшу кількість літер,
 *         треьба модифікувати строку CHARACTERS,
 *         а для забезпечення можливості конфігурації,
 *         треба реалізувати навіть ще більший метод.
 */
        StringBuilder randomString = new StringBuilder(10);
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 10; i++) {
            randomString.append(CHARACTERS.charAt(new Random()
                    .nextInt(CHARACTERS.length())));
        }

/**     Код Selenium для виконання послідовності:
 *      перехід на сайт - очікування на елемент - ввод штучної строки
 *       - переніс курсору - клік */
        driver.manage().window().maximize();
        driver.get(TEST_URL);

        WebElement input_field = driver
                .findElement(By.xpath(INPUT_ELEMENT_TEST_LOCATOR));
        WebElement button = driver
                .findElement(By.xpath(CLICK_ELEMENT_TEST_LOCATOR));
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.visibilityOf(input_field));

        input_field.sendKeys(randomString.toString());

        action.moveToElement(button)
                .click(input_field)
                .build()
                .perform();


    }

    @Test
    public void selmokTest(){

        Selmok selmok = Selmok.compiler()
                .setBrowser(new Browser(new ChromeDriver()))
                .setGenerator(new Generator())
                .compile();

        selmok.browser().browserDriver().manage().window().maximize();
        WebElement input_field = selmok.browser()
                .browserDriver()
                .findElement(By.xpath(INPUT_ELEMENT_TEST_LOCATOR));

        WebElement  button = selmok.browser()
                        .browserDriver()
                                .findElement(By.xpath(CLICK_ELEMENT_TEST_LOCATOR));


        selmok.browser().uiAction().inputValueToElement(input_field,
                selmok.generator()
                        .random()
                        .randomString(10, true, true, false),
                Duration.of(10,ChronoUnit.SECONDS));

        selmok.browser().uiAction().putMouseOverAndClickOnElement(button);

    }
}
