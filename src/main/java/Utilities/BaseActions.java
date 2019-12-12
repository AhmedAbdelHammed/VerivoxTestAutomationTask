package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseActions {

    static JavascriptExecutor javascriptExecutor;

    public static void scrollToElementByLocator(WebDriver driver, By locator){
        javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(locator));
    }

    public static void scrollToElementByWebElement(WebDriver driver, WebElement webElement){
        javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",webElement);
    }

    public static void  clickJSElement(WebDriver driver, WebElement element){
        javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("arguments[0].click();",element);
    }

    public static void clickOnVerivoxLogo(WebDriver driver){
        WebElement verivoxLogo = driver.findElement(ObjectRepo.GENERIC_VERIVOX_LOGO);
        verivoxLogo.click();
    }


}
