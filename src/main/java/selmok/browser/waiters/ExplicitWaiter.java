package selmok.browser.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ExplicitWaiter {
    private WebDriver driver;
    private static ExplicitWaiter explicitWaiter = null;

    private ExplicitWaiter(WebDriver driver) {
        this.driver = driver;
    }

    public static ExplicitWaiter getExplicitWaiter(WebDriver driver){
        if(explicitWaiter == null){
            explicitWaiter = new ExplicitWaiter(driver);
        }

        return explicitWaiter;
    }

//    -------------------------------<General Page Waiters>--------------------------------

    public void waitForPageTitleToBe(String title, boolean contains, Duration timeout){

        if(contains){
            new WebDriverWait(driver, timeout).until(ExpectedConditions.titleContains(title));
        }

        else {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.titleIs(title));
        }

    }

    public void waitForPageUrlToBe(String url, boolean contains, Duration timeout){

        if(contains){
            new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(url));
        }

        else{
            new WebDriverWait(driver, timeout).until(ExpectedConditions.urlToBe(url));
        }
    }

    public void waitForUrlToMatchPattern(String regex, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.urlMatches(regex));
    }

    public void waitUntilPageReadyStateComplete(Duration timeout){
        new WebDriverWait(driver, timeout).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

//    -------------------------------<Page Elements Waiters>--------------------------------

    public void waitForNumberOfElementsToBe(By elementsLocator, int amount, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.numberOfElementsToBe(elementsLocator,
                amount));
    }

    public void waitForPresenceOfElementInDOM(By elementLocator, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void waitForPresenceOfElementsInDOM(By elementsLocator, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementsLocator));
    }

    public void waitForElementToShowUp(By elementLocator, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public void waitForElementToShowUp(WebElement element, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(By elementLocator, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    public void waitForElementToDisappear(WebElement element, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForAllElementsToAppear(Duration timeout, By... elementsLocator){

        for (By elementLocator : elementsLocator) {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
        }
    }

    public void waitForAllElementsToAppear(List<WebElement> elements, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForAllElementsToDisappear(Duration timeout, By... elementsLocator){
        for (By elementLocator : elementsLocator) {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        }
    }

    public void waitForAllElementsToDisappear(List<WebElement> elements, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitForElementToBeClickable(By elementLocator, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public void waitForElementToBeClickable(WebElement element, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementTextToBe(By elementLocator, String expectedText, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.textToBePresentInElementValue(elementLocator, expectedText));
    }

    public void waitForElementTextToBe(WebElement element, String expectedText,
                                       Duration timeout){
    new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public void waitForElementValueToBe(By elementLocator, String expectedValue, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.textToBePresentInElementValue(elementLocator, expectedValue));

    }

    public void waitForElementValueToBe(WebElement element, String expectedValue, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.textToBePresentInElementValue(element, expectedValue));

    }

    public void waitElementAttributeToBe(WebElement element, String attribute, String expectedValue,
                                         Duration timeout){

            new WebDriverWait(driver, timeout).until(ExpectedConditions.attributeContains(element,
                    attribute, expectedValue));
    }

    public void waitForElementToBeDeselected(WebElement element, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementSelectionStateToBe(element,
                false));
    }

    public void waitForElementToBeSelected(WebElement element, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeSelected(element));
    }

    //    -------------------------------<Page Elements Waiters>--------------------------------

    public void waitForFrameToBeActiveAndSwitchToIt(String frameLocator, Duration timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public void waitForFrameToBeActiveAndSwitchToIt(WebElement frame, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void waitForFrameToBeActiveAndSwitchToIt(int frameID, Duration timeout){
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameID));
    }













}
