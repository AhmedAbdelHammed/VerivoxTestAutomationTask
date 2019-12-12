package StepDefs;

import PageObjects.HomePage;
import PageObjects.TariffDetailsPage;
import PageObjects.TariffsResultsPage;
import Utilities.BaseActions;
import Utilities.PropertiesConfig;
import Utilities.WebDriverSingleton;
import cucumber.api.java.Before;
/*import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;*/
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class VerifyResultListStepDefs {

    WebDriver driver;
    PropertiesConfig propertiesConfig;
    HomePage homePage;
    TariffsResultsPage tariffsResultsPage;
    TariffDetailsPage tariffDetailsPage;
    SoftAssert softAssert;

    String speed;
    String prefix;
    int tariffIndexToNavigateTo;
    String tariffPriceInResults;
    String tariffTitleInResults;
    String tariffProviderNameInResults;
    String tariffTitleInDetails;
    int tariffsResultsCount = 0;

    @Before
    public void Initialization(){
        driver = WebDriverSingleton.getInstance().getDriver();
        propertiesConfig = new PropertiesConfig();
        homePage = new HomePage(driver);
        tariffsResultsPage = new TariffsResultsPage(driver);
        tariffDetailsPage = new TariffDetailsPage(driver);
    }

    @Given("the User is on {string}")
    public void theUserIsOn(String baseURL) {
        driver.get(baseURL);
    }

    @When("he is on the DSL calculator")
    public void heIsOnTheDSLCalculator() {
        homePage.acceptTerms();
        homePage.clickOnDSLTab();
    }

    @And("he enters prefix or code Ihre Vorwahl as {string} with {string} bandwidth selection")
    public void heEntersPrefixOrCodeIhreVorwahlAsWithBandwidthSelection(String prefix, String speed) {
        homePage.addDSLPrefix(prefix);
        homePage.selectDSLSpeed(speed);
        this.speed = speed;
        this.prefix = prefix;
    }

    @And("clicks on the button labeled as JETZT VERGLEICHEN")
    public void clicksOnTheButtonLabeledAsJETZTVERGLEICHEN() {
        homePage.goToDSLResults();
    }

    @Then("he should be able see the Result List page with all the available Tariffs")
    public void heShouldBeAbleSeeTheResultListPageWithAllTheAvailableTariffs() {
        Assert.assertTrue(tariffsResultsPage.isTariffResultsCorrect(speed),"tarrifs results speed are incorrect");
    }

    @Given("the User is on the DSL Result List of {string} with {string}")
    public void theUserIsOnTheDSLResultList(String prefix, String speed) {
        softAssert = new SoftAssert(); //using soft assert for the rest of scenarios to determine all the failures because they have more than one check
        BaseActions.clickOnVerivoxLogo(driver);
        homePage.acceptTerms();
        homePage.clickOnDSLTab();
        homePage.addDSLPrefix(prefix);
        homePage.selectDSLSpeed(speed);
        homePage.goToDSLResults();
        this.speed = speed;
        this.prefix = prefix;
    }

    @When("he selects one of the listed Tariffs")
    public void heSelectsOneOfTheListedTariffs() {
        tariffIndexToNavigateTo = 1;
        tariffTitleInResults = tariffsResultsPage.getTariffTitle(tariffIndexToNavigateTo);
        tariffPriceInResults = tariffsResultsPage.getTariffPrice(tariffIndexToNavigateTo);
        tariffProviderNameInResults = tariffsResultsPage.getTariffProviderName(tariffIndexToNavigateTo);
    }

    @And("clicks on {string} or {string} button")
    public void clicksOnButton(String goToDetailsBtnText, String secondGoToDetailsBtnText) {
        softAssert.assertTrue(tariffsResultsPage.isTariffGoToDetailsBtnHaveCorrectText(goToDetailsBtnText, secondGoToDetailsBtnText),"Go to details label is invalid");
        tariffsResultsPage.navigateToTariffDetails(tariffIndexToNavigateTo);
    }

    @Then("he should be able see the details of the selected Tariff")
    public void heShouldBeAbleSeeTheDetailsOfTheSelectedTariff() {
        softAssert.assertTrue(tariffDetailsPage.isNavigateToDetails(),"User is not navigated to tariff details");
    }

    @And("he should also see a button labeled as {string}")
    public void heShouldAlsoSeeAButtonLabeledAs(String buttonLabel) {
        softAssert.assertEquals(tariffDetailsPage.getAvailabilityBtnSText(),buttonLabel,"Availability buttons label is invalid");
    }

    @And("he should find the tariff price the same as in the results")
    public void heShouldFindTheTariffPriceTheSameAsInTheResults() {
        softAssert.assertEquals(tariffDetailsPage.getTariffPrice(), tariffPriceInResults, "Tariff price is not as in the results page");
    }

    @And("he should find the tariff title the same as in the results")
    public void heShouldFindTheTariffTitleTheSameAsInTheResults() {
        softAssert.assertEquals(tariffDetailsPage.getTariffTitle(), tariffTitleInResults,"Tariff title is not as in the results page");
        tariffTitleInDetails = tariffDetailsPage.getTariffTitle();
    }

    @And("he should find the tariff provider name the same as in the results")
    public void heShouldFindTheTariffProviderNameTheSameAsInTheResults() {
        softAssert.assertEquals(tariffDetailsPage.getTariffProviderName(),tariffProviderNameInResults,"Tariff provider name is not as in the results page");
    }

    @And("he should find the effective price tooltip labeled as {string}")
    public void heShouldFindTheEffectivePriceTooltipLabeledAs(String effectivePriceTooltip) {
        softAssert.assertEquals(tariffDetailsPage.getPriceTooltipText(),effectivePriceTooltip, "Price tooltip is not shown or have invalid text");
    }

    @And("he should find the price details title labeled as {string} plus the tarif title")
    public void heShouldFindThePriceDetailsTitleLabeledAsPlusTheTarifTitle(String priceDetailsTitle) {
        softAssert.assertEquals(tariffDetailsPage.getPriceDetailsTitle(),priceDetailsTitle +" "+tariffTitleInDetails,"Price details title is not shown or have invalid text");
    }

    @And("he should find the after twenty four months details label contain {string} text")
    public void heShouldFindTheAfterTwentyFourMonthsDetailsLabelContainText(String twentyFourMonthLabel) {
        softAssert.assertTrue(tariffDetailsPage.getPriceAfterTwentyFourMonthText().contains(twentyFourMonthLabel),"Twenty four month label is not shown or have invalid text ");
        softAssert.assertAll();
    }

    @When("there are more than {int} tariffs available for the provided Vorwahl and bandwidth")
    public void thereAreMoreThanTariffsAvailableForTheProvidedVorwahlAndBandwidth(int numberOfTariffsInResults) {
        tariffsResultsCount += numberOfTariffsInResults;
        //Hard Assert because the scenario shall not complete if this assert failed
        Assert.assertEquals(tariffsResultsPage.getTariffsResultsCount(),numberOfTariffsInResults,"tariffs result should be 20 to complete the scenario");
    }

    @Then("the User should a button labeled as {string}")
    public void theUserShouldAButtonLabeledAs(String buttonLabel) {
        softAssert.assertEquals(tariffsResultsPage.getTwentyMoreTariffsButtonLabel().toLowerCase(),buttonLabel.toLowerCase(),"20 more button label is invalid");
    }

    @And("When he or she clicks on this button")
    public void whenHeOrSheClicksOnThisButton() {
        tariffsResultsPage.clickOnTwentyMoreTariffsButton();
    }

    @Then("the list should be appended with next {int} tariffs and so on until all Tariffs are loaded")
    public void theListShouldBeAppendedWithNextTariffsAndSoOnUntilAllTariffsAreLoaded(int numberOfTariffsToBeAppended) {
        softAssert.assertEquals(tariffsResultsPage.getTariffsResultsCount(),tariffsResultsCount+numberOfTariffsToBeAppended,"20 More Tariff button is not working properly");
        softAssert.assertAll();
    }
}
