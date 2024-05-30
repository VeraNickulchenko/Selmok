package selmok.browser.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selmok.browser.waiters.Waiter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UserAction {

    private JavascriptExecutor driver;
    private Actions actionChains;
    private Waiter waiter;

    private static UserAction uiAction = null;

    private static final Duration DEFAULT_WAIT_TIMEOUT = Duration.of(5, ChronoUnit.SECONDS);

    private UserAction(WebDriver driver) {
        this.driver = (JavascriptExecutor) driver;
        this.actionChains = new Actions(driver);
        this.waiter = Waiter.waiter(driver);
    }

    public static UserAction userAction(WebDriver driver){
        if(uiAction == null){
            uiAction = new UserAction(driver);
        }

        return uiAction;
    }

    public void scrollThePageDown(){
        driver.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollPageUp(){
        driver.executeScript("window.scrollTo(0,0)");
    }


    public void scrollElementIntoView(WebElement element){
        actionChains.scrollToElement(element)
                .build()
                .perform();
    }

    public void clickOnElement(WebElement element, Duration waitTimeout){
        waiter.explicitWaiter().waitForElementToBeClickable(element, waitTimeout);
        element.click();
    }

    public void clickOnElement(WebElement element){
        clickOnElement(element, DEFAULT_WAIT_TIMEOUT);
    }

    public void putMouseOverElement(WebElement element){
        actionChains.moveToElement(element).build().perform();
    }

    public void putMouseOverAndClickOnElement(WebElement element, Duration waitTime){
        waiter.explicitWaiter().waitForElementToBeClickable(element,waitTime);
        actionChains.moveToElement(element)
                .click(element)
                .build()
                .perform();

    }

    public void putMouseOverAndClickOnElement(WebElement element){
        putMouseOverAndClickOnElement(element, DEFAULT_WAIT_TIMEOUT);
    }

    public void putMouseOverAndClickChainedElement(WebElement targetElement, WebElement chainedElement,
                                                   Duration waitTime){

        waiter.explicitWaiter().waitForElementToBeClickable(targetElement, waitTime);
        actionChains.moveToElement(targetElement).build().perform();
        putMouseOverAndClickOnElement(chainedElement);

    }

    public void putMouseOverAndClickChainedElement(WebElement targetElement, WebElement chainedElement){
        putMouseOverAndClickChainedElement(targetElement, chainedElement, DEFAULT_WAIT_TIMEOUT);
    }

    public void clickMouseOverAndClickChainedElement(WebElement targetElement, WebElement chainedElement,
                                                     Duration waiterTime){
        waiter.explicitWaiter().waitForElementToBeClickable(targetElement, waiterTime);
        actionChains.click(targetElement).moveToElement(chainedElement).click(chainedElement)
                .build().perform();

    }

    public void clickMouseOverAndClickChainedElement(WebElement targetElement, WebElement chainedElement){
        clickMouseOverAndClickChainedElement(targetElement, chainedElement, DEFAULT_WAIT_TIMEOUT);
    }


    public void inputValueToElement(WebElement input, String value, Duration waitTime){
        waiter.explicitWaiter().waitForElementToBeClickable(input, waitTime);
        input.clear();
        input.sendKeys(value);
    }

    public void inputValueToElement(WebElement input, String value){
        inputValueToElement(input, value, DEFAULT_WAIT_TIMEOUT);
    }

    public void inputValueToElementWithPauses(WebElement input, String value, Duration inputInterval,
                                              Duration fieldTimeout){

        boolean hasValueAttribute = input.getAttribute("value") != null;
        char[] valueCharacters = value.toCharArray();
        input.clear();

        for (char character :
                valueCharacters) {

            String charStr = String.valueOf(character);
            inputValueToElement(input, charStr, fieldTimeout);

            if(!hasValueAttribute){
                waiter.explicitWaiter().waitForElementTextToBe(input, charStr, inputInterval);
            }

            else{
                waiter.explicitWaiter().waitForElementValueToBe(input, charStr, inputInterval);
            }

        }
    }

    public void clearElement(WebElement input, Duration waitTime){
        waiter.explicitWaiter().waitForElementToBeClickable(input, waitTime);
        input.clear();
    }

    public void clearElement(WebElement input){
        clearElement(input, DEFAULT_WAIT_TIMEOUT);
    }

    public String getTextFromElement(WebElement element){
        return element.getText();
    }

    public String getAttributeFromElement(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public String getValueFromElement(WebElement element){
        return getAttributeFromElement(element, "value");
    }

    public List<String> getTextFromElements(List<WebElement> elements){
        List<String> elementText = new ArrayList<>();

        for (WebElement element :
                elements) {
            elementText.add(getTextFromElement(element));
        }

        return elementText;
    }

    public List<String> getAttributeFromElements(List<WebElement> elements, String attribute){
        List<String> elementsAttributes = new ArrayList<>();

        for (WebElement element : elements) {
            elementsAttributes.add(element.getAttribute(attribute));
        }

        return elementsAttributes;
    }

    public List<String> getValuesFromElements(List<WebElement> elements){
        return getAttributeFromElements(elements, "value");
    }

    public boolean isElementEnabled(WebElement element){
        return element.isEnabled();
    }

    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element){
        return element.isSelected();
    }

    public boolean doesElementTextEqualToOrContain(WebElement element, String expectedText,
                                                   boolean contains){

        String elementText = element.getText();

        if(contains){
            return elementText.contains(expectedText);
        }
        return elementText.equals(expectedText);
    }

    public boolean doesElementValueEqualToOrContain(WebElement element, String expectedValue,
                                                     boolean contains){
        return doesElementAttributeEqualsToOrContain(element, "value", expectedValue,
                contains);
    }

    public boolean doesElementAttributeEqualsToOrContain(WebElement element, String attributeName,
                                                          String expectedValue,
                                                          boolean contains){
        String attributeValue = element.getAttribute(attributeName);

        if(attributeValue == null){
            return false;
        }

        if(contains){
            return attributeValue.contains(expectedValue);
        }

        return attributeValue.equals(expectedValue);
    }

    public boolean doesAllElementHasText(List<WebElement> elements, String expectedText,
                                         boolean contains){
        return elements.stream()
                .allMatch(element -> doesElementTextEqualToOrContain(element, expectedText, contains));
    }

    public boolean doesAllElementsHasValue(List<WebElement> elements, String expectedValue,
                                           boolean contains){
        return elements.stream()
                .allMatch(element -> doesElementValueEqualToOrContain(element, expectedValue, contains));
    }

    public Actions actionChainsObj(){
        return actionChains;
    }











}
