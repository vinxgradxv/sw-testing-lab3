package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.CommunityPage;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CommunityTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> findPlayerTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Найти игрока по логину " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var communityPage = new CommunityPage(driver);
                        communityPage.findPlayer();
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//a[contains(text(),'max')]")));
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> inviteFriendTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Пригласить друга в команду " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(Utils.CORRECT_EMAIL, Utils.CORRECT_PASSWORD);
                        var communityPage = new CommunityPage(driver);
                        communityPage.inviteFriend();
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//a[contains(text(),'РЕФЕРАЛЬНАЯ ССЫЛКА')]")));
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> goToDiscordTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Перейти на Discord " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var communityPage = mainPage.goToCommunityPage();
                        communityPage.goToDiscord();
                        Thread.sleep(5000);
                        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
                        driver.switchTo().window(browserTabs .get(1));
                        Assertions.assertEquals("https://discord.com/invite/world-of-tanks", driver.getCurrentUrl());
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> goToYoutubeTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Перейти на Discord " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var communityPage = mainPage.goToCommunityPage();
                        communityPage.goToYoutube();
                        Thread.sleep(5000);
                        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
                        driver.switchTo().window(browserTabs .get(1));
                        Assertions.assertEquals("https://www.youtube.com/c/WorldOfTanksEurope", driver.getCurrentUrl());
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> goToTwitchTest() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Перейти на Discord " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var communityPage = mainPage.goToCommunityPage();
                        communityPage.goToTwitch();
                        Thread.sleep(5000);
                        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
                        driver.switchTo().window(browserTabs .get(1));
                        Assertions.assertEquals("https://www.twitch.tv/worldoftanks/", driver.getCurrentUrl());
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
