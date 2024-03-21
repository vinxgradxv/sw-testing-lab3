package ru.tinkoff.ps.ops.test.swt.model;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class SignUpPage extends Page{
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void createAccount() {
        Faker faker = new Faker();
        String randomString = faker.lorem().characters(10);

        var country = Utils.getElementBySelector(driver, By.xpath("//select[@name=\"country_code\"]"));
        Select selectCountry = new Select(country);
        selectCountry.selectByIndex(1);

        var inputName = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_name']"));
        inputName.clear();
        inputName.sendKeys(randomString);

        var inputLogin = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_login']"));
        inputLogin.clear();
        inputLogin.sendKeys(randomString);

        var inputPassword = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_password']"));
        inputPassword.clear();
        inputPassword.sendKeys(randomString);

        var inputRePassword = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_re_password']"));
        inputRePassword.clear();
        inputRePassword.sendKeys(randomString);

    }
}
