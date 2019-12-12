package PageObjects;

import Utilities.ObjectRepo;
import Utilities.PropertiesConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TariffDetailsPage {
    WebDriver driver;
    WebDriverWait wait;
    PropertiesConfig propertiesConfig;

    public TariffDetailsPage(WebDriver driver) {
        this.driver = driver;
        propertiesConfig = new PropertiesConfig();
        wait = new WebDriverWait(this.driver,propertiesConfig.getWaitTimeout());
    }

    //Used to get first availability btn text
    public String getAvailabilityBtnSText(){
        String script = "return window.getComputedStyle(document.querySelector('.'+'responsive-label-txt.offer-page-cta'),':before').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String content = (String) js.executeScript(script);
        System.out.println(content.replaceAll("\"","").trim());
        content = content.replaceAll("\"","").trim();
        return content;
    }

    //Used to get tariff provider name
    public String getTariffProviderName(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepo.TARIFFSPAGE_DETAILS_PROVIDER_NAME));
        WebElement tariffProviderName = driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_PROVIDER_NAME);
        return tariffProviderName.getText();
    }

    //Used to get tariff title
    public String getTariffTitle(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_TITLE));
        WebElement tariffTitle = driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_TITLE);
        return tariffTitle.getText();
    }


    //Used to get tariff price available on details
    public String getTariffPrice(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepo.TARIFFSPAGE_DETAILS_PRICE));
        List<WebElement> tariffPriceOnPage = driver.findElements(ObjectRepo.TARIFFSPAGE_DETAILS_PRICE);
        return tariffPriceOnPage.get(0).getText();
    }

    //Used to get price tooltip text
    public String getPriceTooltipText(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_DETAILS_EFFECTIVE_PRICE_TOOLTIP));
        WebElement effectivePriceTooltip = driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_EFFECTIVE_PRICE_TOOLTIP);
        return effectivePriceTooltip.getText().trim();
    }

    //Used to get price details title
    public String getPriceDetailsTitle(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_PRICE_DETAILS_TITLE));
        WebElement tariffPriceTitle = driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_PRICE_DETAILS_TITLE);
        return tariffPriceTitle.getText();
    }

    //Used to get price after 24 months text
    public String getPriceAfterTwentyFourMonthText(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_PRICE_COST_AFTER_24_MONTHS));
        WebElement twentyFourMonthsTooltip = driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_TARIFF_PRICE_COST_AFTER_24_MONTHS);
        return twentyFourMonthsTooltip.getText();
    }

    //Used to check if navigated to tariff details successfully or not
    public boolean isNavigateToDetails(){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.TARIFFSPAGE_DETAILS_FIRST_AVAILABILITY_BTN));
            driver.findElement(ObjectRepo.TARIFFSPAGE_DETAILS_FIRST_AVAILABILITY_BTN);
        } catch (Exception e){
            return false;
        } return true;
    }

}
