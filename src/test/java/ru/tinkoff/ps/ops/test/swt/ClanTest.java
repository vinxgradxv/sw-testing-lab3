package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

public class ClanTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> createClanNoMoneyTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Создание клана без денег " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var clanPage = mainPage.goToClanPage();
                        clanPage.createClan();
                        Assertions.assertTrue(true);
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> pickClanTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Создание заявки для выбора клана" + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        mainPage.rejectPromoWindow();
                        var clanPage = mainPage.goToClanPage();
                        clanPage.pickClan();
                        var loginPage = clanPage.toLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        clanPage.pickProperties();
                        Assertions.assertTrue(true);
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> searchClanTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Поиск клана" + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var clanPage = mainPage.goToClanPage();
                        clanPage.searchClan();
                        Utils.changeTab(driver);
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'ITMO')]")));
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
