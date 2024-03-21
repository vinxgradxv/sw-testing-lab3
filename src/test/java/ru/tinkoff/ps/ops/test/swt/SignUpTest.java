package ru.tinkoff.ps.ops.test.swt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import ru.tinkoff.ps.ops.test.swt.model.MainPage;

import java.util.stream.Stream;

public class SignUpTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> successfulSignUp() {
        return Utils.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Успешная регистрация браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(Utils.BASE_URL);
                        var signUpPage = mainPage.goToSignUpPage();
                        signUpPage.createAccount();
                        Assertions.assertNotNull(Utils.getElementBySelector(driver, By.xpath("//h1[contains(text(),'АККАУНТ СОЗДАН')]")));
                        Assertions.assertTrue(signUpPage.getDriver().getCurrentUrl().contains("https://eu.wargaming.net"));
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
