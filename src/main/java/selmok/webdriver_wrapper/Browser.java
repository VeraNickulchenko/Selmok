package selmok.webdriver_wrapper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import selmok.webdriver_wrapper.actions.UserAction;
import selmok.webdriver_wrapper.waiters.Waiter;

public class Browser {

    private WebDriver driver;

    private JavascriptExecutor jsDriver;

    private static Browser browser = null;

    public Browser(WebDriver driver) {
        this.driver = driver;
        this.jsDriver = (JavascriptExecutor) driver;
    }

    public static Browser driverWrapper(WebDriver driver){
        if(browser == null){
            browser = new Browser(driver);
        }

        return browser;
    }

    public void closeBrowser(){
        driver.close();
    }

    public WebDriver browserDriver(){
        return driver;
    }

    public JavascriptExecutor jsExecutorDriver(){
        return jsDriver;
    }

    public Waiter waiter(){
        return Waiter.waiter(driver);
    }

    public UserAction uiAction(){
        return UserAction.userAction(driver);
    }


}
