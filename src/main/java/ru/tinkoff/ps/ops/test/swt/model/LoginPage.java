package ru.tinkoff.ps.ops.test.swt.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class LoginPage extends Page{

    public final String loginButtonXpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/span[1]/form[1]/div[1]/fieldset[2]/span[1]/button[1]";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String login, String password) {
        //var rejectCookieButton = Utils.getElementBySelector(driver, By.xpath("//button[@id='onetrust-reject-all-handler']"), 60);
        //rejectCookieButton.click();
        var inputLogin = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_login']"));
        inputLogin.clear();
        inputLogin.sendKeys(login);
        var inputPassword = Utils.getElementBySelector(driver, By.xpath("//input[@id='id_password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        var loginButton = Utils.getElementBySelector(driver, By.xpath(loginButtonXpath));
        loginButton.click();
    }
}
