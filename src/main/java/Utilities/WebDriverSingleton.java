package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {
    private static WebDriverSingleton webDriverSingleton = null;
    private WebDriver driver;
    PropertiesConfig propertiesConfig;

    private WebDriverSingleton(){
        WebDriverManager.chromedriver().setup();
        propertiesConfig = new PropertiesConfig();
        ChromeOptions options = new ChromeOptions();
        if(propertiesConfig.runHeadless()){
            options.addArguments("--headless");
        }
        //options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        //options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public static WebDriverSingleton getInstance(){
        if(webDriverSingleton == null){
            webDriverSingleton = new WebDriverSingleton();
        }
        return webDriverSingleton;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
