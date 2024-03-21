package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class ClanTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> createClanNoMoneyTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Успешный вход в аккаунт в браузере " + driver.getClass(),
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

}
