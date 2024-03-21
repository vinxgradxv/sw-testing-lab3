package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.util.stream.Stream;


public class LoginTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }


    @TestFactory
    public Stream<DynamicTest> testLoginWithMail() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Успешный вход в аккаунт в браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'" + Utils.CORRECT_LOGIN + "')]")));
                        Assertions.assertEquals("https://worldoftanks.eu/ru/", loginPage.getDriver().getCurrentUrl());
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> testLoginWithLogin() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Не успешный вход в аккаунт (логин вместо почты) в браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_LOGIN, Utils.CORRECT_PASSWORD);
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath(loginPage.loginButtonXpath)));
                        Assertions.assertTrue(loginPage.getDriver().getCurrentUrl().contains("eu.wargaming.net"));
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> testLoginWithWrongPassword() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Не успешный вход в аккаунт (неверный пароль) в браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.WRONG_PASSWORD);
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//p[contains(text(),'Неверный email или пароль.')]")));
                        Assertions.assertTrue(loginPage.getDriver().getCurrentUrl().contains("eu.wargaming.net"));
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
