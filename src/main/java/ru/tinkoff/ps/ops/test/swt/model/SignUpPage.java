package ru.tinkoff.ps.ops.test.swt.model;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.tinkoff.ps.ops.test.swt.Utils;

import javax.xml.xpath.XPath;

public class SignUpPage extends Page{
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void createAccount() {
        Faker faker = new Faker();
        String randomString = faker.lorem().characters(10);

        var country = Utils.getElementBySelector(driver, By.xpath("//input[@placeholder=\"Место жительства\"]"));
        country.click();
        var koreaCountry = Utils.getElementBySelector(driver, By.xpath("//li[@data-value=\"KR\"]"));
        koreaCountry.click();

        var inputName = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_name']"));
        inputName.clear();
        inputName.sendKeys(randomString);

        var inputLogin = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_login']"));
        inputLogin.clear();
        inputLogin.sendKeys(randomString  + "@mail.ru");

        var inputPassword = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_password']"));
        inputPassword.clear();
        inputPassword.sendKeys(randomString + "1A");

        var inputRePassword = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_re_password']"));
        inputRePassword.clear();
        inputRePassword.sendKeys(randomString + "1A");

        var cookie = Utils.getElementBySelector(driver, By.xpath("//button[@aria-label=\"Закрыть\"]"));
        cookie.click();


        var inputDay = Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'День')]"));
        inputDay.click();
        var inputDay1 = Utils.getElementBySelector(driver, By.xpath("//li[@data-value=\"01\"]"));
        inputDay1.click();

        var inputMonth = Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'Месяц')]"));
        inputMonth.click();
        var inputMonthJan = Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'Январь')]"));
        inputMonthJan.click();

        var inputYear = Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'Год')]"));
        inputYear.click();
        var inputYear2024 = Utils.getElementBySelector(driver, By.xpath("//li[@data-value=\"1999\"]"));
        inputYear2024.click();

        var licenseInput= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[6]/form[1]/fieldset[10]/ul[1]/li[1]/input[1]"));
        licenseInput.sendKeys(" ");

        var cont = Utils.getElementBySelector(driver, By.xpath("//span[contains(text(),'Продолжить')]"));
        cont.click();
    }
}
