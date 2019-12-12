package PageObjects;

import Utilities.BaseActions;
import Utilities.ObjectRepo;
import Utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TariffsResultsPage {
    WebDriver driver;
    WebDriverWait wait;
    PropertiesConfig propertiesConfig;

    public TariffsResultsPage(WebDriver driver) {
        this.driver = driver;
        propertiesConfig = new PropertiesConfig();
        wait = new WebDriverWait(this.driver,propertiesConfig.getWaitTimeout());
    }

    //Used to get the count of tariffs results in the page
    public int getTariffsResultsCount(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_RESULTS_BLOCKS));
        waitForLoaderToDisappear();
        //wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.TARIFFSPAGE_RESULTS_GOTODETAILS_BTN));
        List<WebElement> tariffsResults = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_BLOCKS);
        return tariffsResults.size();
    }

    //Used to get every tariff result download speed, will be used to check if results is correct
    public List<String> getTariffDownloadSpeed() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIIFSPAGE_RESULTS_SPEED));
        List<WebElement> tariffsSpeed = driver.findElements(ObjectRepo.TARIIFSPAGE_RESULTS_SPEED);
        List<String> tariffsDownloadSpeed = new ArrayList<String>();
        for (int i = 0; i < tariffsSpeed.size(); i++) {
            if(i%2==0) tariffsDownloadSpeed.add(tariffsSpeed.get(i).getText());
        }
        return tariffsDownloadSpeed;
    }

    //Used to check if all the tariffs results have correct download speed according to the search or not
    public boolean isTariffResultsCorrect(String tariffProvidedInCompare){
        List<String> tariffsDownloadSpeed = getTariffDownloadSpeed();
        int trueCount = 0;
        for(int i=0; i<tariffsDownloadSpeed.size();i++){
            if(Integer.parseInt(tariffsDownloadSpeed.get(i).replaceAll("[\\D]", "")) >= Integer.parseInt(tariffProvidedInCompare.replaceAll("[\\D]", ""))){
                trueCount++;
                System.out.println("Tariff Download: "+tariffsDownloadSpeed.get(i));
            }
        }
        return (trueCount == tariffsDownloadSpeed.size());
    }

    //Used to navigate to specific tariff details page
    public void navigateToTariffDetails(int tariffIndex){
        tariffIndex = tariffIndex - 1; //to match the index of array
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_RESULTS_GOTODETAILS_BTN));
        List<WebElement> tariffsGoToDetailsBtnS = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_GOTODETAILS_BTN);
        BaseActions.scrollToElementByWebElement(driver,tariffsGoToDetailsBtnS.get(tariffIndex));
        BaseActions.clickJSElement(driver,tariffsGoToDetailsBtnS.get(tariffIndex));
    }

    //Check if tariff go to details button have the correct text
    public boolean isTariffGoToDetailsBtnHaveCorrectText(String goToDetailsBtnText, String secondGoToDetailsBtnText){
        int trueCount = 0;
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_RESULTS_GOTODETAILS_BTN));
        List<WebElement> tariffsGoToDetailsBtnS = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_GOTODETAILS_BTN);
        for(int i=0; i<tariffsGoToDetailsBtnS.size(); i++){
            System.out.println("tariff button text: "+tariffsGoToDetailsBtnS.get(i).getText());
            if(tariffsGoToDetailsBtnS.get(i).getText().toLowerCase().equals(goToDetailsBtnText.toLowerCase()) ||
                    tariffsGoToDetailsBtnS.get(i).getText().toLowerCase().equals(secondGoToDetailsBtnText.toLowerCase())){
                System.out.println("tariff button text: "+tariffsGoToDetailsBtnS.get(i).getText());
                trueCount++;
            }
        }
        System.out.println("tariff button text: "+trueCount);
        return  trueCount == tariffsGoToDetailsBtnS.size();
    }

    //Get the title of specific tariff in the results page
    public String getTariffProviderName(int tariffIndex){
        tariffIndex = tariffIndex - 1; //to match the index of array
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_RESULTS_TARIFF_TITLE));
        List<WebElement> tariffsTitles = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_TARIFF_TITLE);
        return tariffsTitles.get(tariffIndex).getText();
    }

    //Get the provider name of specific tariff in the results page
    public String getTariffTitle(int tariffIndex){
        tariffIndex = tariffIndex - 1; //to match the index of array
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.TARIFFSPAGE_RESULTS_TARIFF_PROVIDER_NAME));
        List<WebElement> tariffsProviderNames = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_TARIFF_PROVIDER_NAME);
        return tariffsProviderNames.get(tariffIndex).getText();
    }

    //Get the price of specific tariff in the results page
    public String getTariffPrice(int tariffIndex){
        tariffIndex = tariffIndex - 1; //to match the index of array
        List<WebElement> tariffsPrices = driver.findElements(ObjectRepo.TARIFFSPAGE_RESULTS_TARIFF_PRICE);
        return tariffsPrices.get(tariffIndex).getText();
    }

    //Get the label of 20 more tariffs button
    public String getTwentyMoreTariffsButtonLabel(){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.TARIFFSPAGE_RESULTS_MORE_BTN));
        WebElement moreButton = driver.findElement(ObjectRepo.TARIFFSPAGE_RESULTS_MORE_BTN);
        return moreButton.getText().trim();
    }

    //Press on 20 more tariffs button
    public void clickOnTwentyMoreTariffsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.TARIFFSPAGE_RESULTS_MORE_BTN));
        BaseActions.scrollToElementByLocator(driver,ObjectRepo.TARIFFSPAGE_RESULTS_MORE_BTN);
        WebElement moreButton = driver.findElement(ObjectRepo.TARIFFSPAGE_RESULTS_MORE_BTN);
        moreButton.click();
    }

    public void waitForLoaderToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ObjectRepo.GENERIC_OVERLAY_LOADER));
    }

}
