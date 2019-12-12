package Utilities;

import org.openqa.selenium.By;

public class ObjectRepo {

    //HomePage Locators
    public static By HOMEPAGE_GDPR_ACCEPT = By.className("gdpr-vx-consent-bar-button");
    public static By HOMEPAGE_DSL_TAB = By.id("mps-tab-5");
    public static By HOMEPAGE_DSL_PREFIX_FIELD = By.name("Prefix");
    public static By HOMEPAGE_DSL_SPEED_OPTIONS = By.xpath("//*[contains(@id,'calc-dsl-option')]");
    public static By HOMEPAGE_DSL_SUBMIT_BTN = By.xpath("//*[@id='mps-tab-box-5']/form/div[2]/button");

    //Tariff Results Page Locators
    public static By TARIFFSPAGE_RESULTS_BLOCKS = By.cssSelector(".col-12.px-1.px-lg-3");
    public static By TARIIFSPAGE_RESULTS_SPEED = By.xpath("//*[@class='internet-speed-literal col px-0']/b");
    public static By TARIFFSPAGE_RESULTS_GOTODETAILS_BTN = By.xpath("//*[@class='button-primary w-100']");
    public static By TARIFFSPAGE_RESULTS_TARIFF_TITLE = By.className("headline-large");
    public static By TARIFFSPAGE_RESULTS_TARIFF_PROVIDER_NAME = By.className("headline-short-name");
    public static By TARIFFSPAGE_RESULTS_TARIFF_PRICE = By.xpath("//*[@class='position-relative large mb-2']/strong");
    public static By TARIFFSPAGE_RESULTS_MORE_BTN = By.cssSelector(".btn.btn-primary.text-uppercase");

    //Tariff Details Page Locators
    public static By TARIFFSPAGE_DETAILS_FIRST_AVAILABILITY_BTN = By.xpath("//*[@data-description='firstAvailabilityCheckButton']");
    public static By TARIFFSPAGE_DETAILS_SECOND_AVAILABILITY_BTN = By.xpath("//*[@data-description='secondAvailabilityCheckButton']");
    public static By TARIFFSPAGE_DETAILS_EFFECTIVE_PRICE_TOOLTIP = By.className("effective-price-label");
    public static By TARIFFSPAGE_DETAILS_PRICE = By.className("price");
    public static By TARIFFSPAGE_DETAILS_PROVIDER_NAME = By.xpath("//*[@data-description='providerName']");
    public static By TARIFFSPAGE_DETAILS_TARIFF_TITLE =  By.xpath("//*[@class='tariff-details']/h3");
    public static By TARIFFSPAGE_DETAILS_TARIFF_PRICE_DETAILS_TITLE = By.className("price-detail-tariff-name");
    public static By TARIFFSPAGE_DETAILS_TARIFF_PRICE_COST_AFTER_24_MONTHS = By.className("costs-after-24-months");

    //Generic Locators
    public static By GENERIC_VERIVOX_LOGO = By.xpath("//*[@itemprop='logo']");
    public static By GENERIC_OVERLAY_LOADER = By.className("overlay");
}
