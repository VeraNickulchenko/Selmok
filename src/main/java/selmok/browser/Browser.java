package selmok.browser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import selmok.browser.actions.UserAction;
import selmok.browser.waiters.Waiter;

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
