package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class DownloadGameTest {

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
                        var gamePage = mainPage.goToGamePage();
                        gamePage.downloadGame();
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//strong[contains(text(),'Спасибо за загрузку!')]")));

                        Thread.sleep(20000);
                        // Specify the directory path
                        String directoryPath = "C:\\Users\\vinxg\\IdeaProjects\\sw-testing-lab3\\files";

                        // Create a File object for the directory
                        File directory = new File(directoryPath);

                        // Get all files inside the directory
                        var files = Arrays.stream(directory.listFiles()).toList();
                        String expectedFileName = "world_of_tanks_install";
                        var installers = files.stream().filter(file -> file.getName().contains(expectedFileName)).toList();

                        Assertions.assertEquals(1, installers.size());
                        files.forEach(File::delete);
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
