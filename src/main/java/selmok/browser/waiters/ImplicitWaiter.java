package selmok.browser.waiters;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ImplicitWaiter {
    private WebDriver driver;

    public static ImplicitWaiter implicitWaiter = null;

    private ImplicitWaiter(WebDriver driver) {
        this.driver = driver;
    }

    public static ImplicitWaiter getImplicitWaiter(WebDriver driver){
        if(implicitWaiter == null){
            implicitWaiter = new ImplicitWaiter(driver);
        }

        return implicitWaiter;
    }


    public void implicitlyWait(Duration timeout){
        driver.manage().timeouts().implicitlyWait(timeout);
    }

    public void waitForPageLoadTimeout(Duration timeout){
        driver.manage().timeouts().pageLoadTimeout(timeout);
    }

    public void waitForJSTimeout(Duration timeout){
        driver.manage().timeouts().scriptTimeout(timeout);
    }
}
