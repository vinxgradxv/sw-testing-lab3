package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.util.stream.Stream;

public class TournamentTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> takePartInTournamentFindTeam() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Участвовать в турнире, вступив в команду " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var tournamentPage = mainPage.goToTournamentPage();
                        tournamentPage.takePartInTournamentJoinTeam();
                        Assertions.assertTrue(true);
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> takePartInTournamentCreateTeam() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Участвовать в турнире, создав команду " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var tournamentPage = mainPage.goToTournamentPage();
                        tournamentPage.takePartInTournamentCreateTeam();
                        Assertions.assertTrue(true);
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
