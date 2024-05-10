package selmok.webdriver_wrapper.waiters;

import org.openqa.selenium.WebDriver;



public class Waiter {

    private ImplicitWaiter implicitWaiter;

    private ExplicitWaiter explicitWaiter;

    private static  Waiter waiter = null;

    private Waiter(WebDriver driver) {
        this.implicitWaiter = ImplicitWaiter.getImplicitWaiter(driver);
        this.explicitWaiter = ExplicitWaiter.getExplicitWaiter(driver);
    }

    public static Waiter waiter(WebDriver driver){
        if(waiter == null){
            waiter = new Waiter(driver);
        }

        return waiter;
    }

    public ImplicitWaiter implicitWaiter(){
        return implicitWaiter;
    }

    public ExplicitWaiter explicitWaiter(){
        return explicitWaiter;
    }



}


