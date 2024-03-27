package ru.tinkoff.ps.ops.test.swt;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Utils {

    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "drivers/chromedriver.exe";
    public static final String FIREFOX_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_SYSTEM_PROPERTY_PATH = "drivers/geckodriver";
    public static final String BASE_URL = "https://worldoftanks.ru/";

    public static final String CORRECT_EMAIL = "vinxgradxv@gmail.com";
    public static final String CORRECT_LOGIN = "test_itmo";
    public static final String CORRECT_PASSWORD = "12345678Aa";
    public static final String WRONG_PASSWORD = "ktu-kruto";

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("worldoftanks.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver()); return drivers;
                        case "firefox":
                            drivers.add(getFirefoxDriver()); return drivers;
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            return drivers;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Web driver is not specified");
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Chrome driver not set properly");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "C:\\Users\\vinxg\\IdeaProjects\\sw-testing-lab3\\files");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(FIREFOX_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Firefox driver not set properly");
        }
        return new FirefoxDriver();
    }

    public static void enterSiteAndAcceptAdultConfirmation(WebDriver driver) {
        driver.get(Utils.BASE_URL);
        WebElement continueButton = getElementBySelector(driver, By.xpath(".//div[@id='warning_popup']/div/div/div[2]/div[4]/a[1]"));
        continueButton.click();
    }

    public static void login(WebDriver driver) {
        WebElement loginButton = getElementBySelector(driver, By.xpath(".//*[@id='header_login']/a"));
        loginButton.click();
        WebElement loginInput = getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_username']"));
        WebElement loginPassword = getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_password']"));
        WebElement authButton = getElementBySelector(driver, By.xpath(".//*[@id='header_login_popup']/form/div/div[2]/button"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(CORRECT_LOGIN);
        loginPassword.sendKeys(CORRECT_PASSWORD);
        authButton.click();
    }

    public static void prepareDrivers() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        System.setProperty(FIREFOX_SYSTEM_PROPERTY_NAME, FIREFOX_SYSTEM_PROPERTY_PATH);
    }

    public static WebElement getClickableElementBySelector(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        return driverWait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement getElementBySelectorNoVisible(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector, int timeout) {
        WebDriverWait driverWait = new WebDriverWait(driver, timeout);
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitForElement(WebDriver driver, By selector){
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitUntilPageLoads(WebDriver driver, long timeout) {
        WebDriverWait waitDriver = new WebDriverWait(driver, timeout);
        waitDriver.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void changeTab(WebDriver driver) {
        String originalTab = driver.getWindowHandle();

        Set<String> allTabs = driver.getWindowHandles();
        for(String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }
}
