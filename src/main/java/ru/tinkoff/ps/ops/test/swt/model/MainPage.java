package ru.tinkoff.ps.ops.test.swt.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void doLogout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header_login\"]/div/div[3]"))).perform();
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header_login\"]/div/div[3]/div[2]/div[1]/ul/li[10]/a"))).click().perform();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void doFastSearch() {
        WebElement fastSearchButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"bc_tags\"]/div[1]"));
        fastSearchButton.click();
        WebElement russiaRadio = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"fl_quick_search\"]/div[1]/div[3]/div[2]/ul/li[3]/label/span"));
        WebElement searchButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"fl_quick_search\"]/div[1]/div[4]/button[1]"));
        russiaRadio.click();
        searchButton.click();
    }

    public String goToModelViewPage() {
        WebElement firstModel = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"mls_models\"]/div[1]/div[1]/a/picture/img"));
        String modelName = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"mls_models\"]/div[1]/div[1]/div[1]/div[1]/a")).getText();
        firstModel.click();
        return modelName;
    }

    public void inviteFriend() {
        Utils.waitUntilPageLoads(driver, 10);
        Utils.getElementBySelector(driver, By.xpath("//a[@href=\"/followers/labitmo/followers\"]")).click();
        Utils.waitUntilPageLoads(driver, 10);
        Utils.getElementBySelector(driver, By.xpath("//a[@href=\"/auth/oid/new/?next=/ru/\"]")).click();
        Utils.waitUntilPageLoads(driver, 10);
        WebElement emailInput = Utils.getElementBySelector(driver, By.xpath("//input[@id=\"send_invitation_friends_email\"]"));
        emailInput.clear();
        emailInput.sendKeys("abit@ifmo.ru");
        Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div[8]/div/div/div/div/div/div[3]/div[3]/div[1]/form/div/div[2]/button")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public LoginPage goToLoginPage() {
        //var rejectCookieButton = Utils.getElementBySelector(driver, By.xpath("//button[@id='onetrust-reject-all-handler']"));
        //rejectCookieButton.click();
        WebElement closeAddButton = Utils.getElementBySelector(driver, By.xpath("//button[@data-tooltip-text=\"Свернуть\"]"));
        closeAddButton.click();

        var loginButton = Utils.getElementBySelector(driver, By.xpath("//a[contains(text(),'Войти')]"));
        loginButton.click();

        return new LoginPage(this.driver);
    }

    public SignUpPage goToSignUpPage() {
        WebElement spanSignUp = Utils.getElementBySelector(driver, By.xpath("//span[@class=\"big-button_text big-button_text__huge\"][contains(text(), \"Зарегистрироваться\")]"));
        WebElement buttonSignUp = spanSignUp.findElement(By.xpath(".."));
        buttonSignUp.click();

        return new SignUpPage(this.driver);
    }

}

