package PageObjects;

import Utilities.ObjectRepo;
import Utilities.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    PropertiesConfig propertiesConfig;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        propertiesConfig = new PropertiesConfig();
        wait = new WebDriverWait(this.driver, propertiesConfig.getWaitTimeout());
    }

    //Used to accept the terms if the banner available
    public void acceptTerms(){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.HOMEPAGE_DSL_TAB));
        try {
            WebElement gdprAcceptBtn = driver.findElement(ObjectRepo.HOMEPAGE_GDPR_ACCEPT);
            gdprAcceptBtn.click();
        } catch (Exception e){
            //Do Nothing
        }
    }

    //Used to click on DSL tab on homepage
    public void clickOnDSLTab(){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.HOMEPAGE_DSL_TAB));
        WebElement dslTab = driver.findElement(ObjectRepo.HOMEPAGE_DSL_TAB);
        dslTab.click();
    }

    //Used to add DSL prefix on DSL Calc
    public void addDSLPrefix(String prefix){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.HOMEPAGE_DSL_PREFIX_FIELD));
        WebElement prefixField = driver.findElement(ObjectRepo.HOMEPAGE_DSL_PREFIX_FIELD);
        prefixField.sendKeys(prefix);
    }

    //Used to select specific speed on DSL Calc
    public void selectDSLSpeed(String speed){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ObjectRepo.HOMEPAGE_DSL_SPEED_OPTIONS));
        List<WebElement> speedOptions = driver.findElements(ObjectRepo.HOMEPAGE_DSL_SPEED_OPTIONS);
        for(int i=0; i<speedOptions.size(); i++){
            //Comparing the strings by trimming all white spaces
            if(speedOptions.get(i).getText().replaceAll("\\s+","").equals(speed.replaceAll("\\s+",""))){
                speedOptions.get(i).click();
                break;
            }
        }
    }

    //Used to click on Compare to go to results from DSL Calc
    public void goToDSLResults(){
        wait.until(ExpectedConditions.elementToBeClickable(ObjectRepo.HOMEPAGE_DSL_SUBMIT_BTN));
        WebElement submitBtn = driver.findElement(ObjectRepo.HOMEPAGE_DSL_SUBMIT_BTN);
        submitBtn.click();
    }


}
