package ru.tinkoff.ps.ops.test.swt.model;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ClanPage extends Page{
    public ClanPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public void createClan() {
        var createButton = Utils.getElementBySelector(driver, By.xpath("//header/div[1]/div[1]/a[2]"));
        createButton.click();

        changeTab();
        Thread.sleep(10000);
        var inputName = driver.findElement(By.xpath("//input[@id='clan_name']"));

        inputName.clear();
        inputName.sendKeys("adsfasdfdf");

        var inputTag = Utils.getElementBySelector(driver, By.xpath("//input[@id='clan_tag']"));
        inputTag.clear();
        inputTag.sendKeys("FD212");
    }

    @SneakyThrows
    public void pickClan() {
        var pickButton = Utils.getElementBySelector(driver, By.xpath("//header/div[1]/div[1]/a[1]"));
        pickButton.click();

        changeTab();
    }

    public void pickProperties() {
        var changeRequestButton = Utils.getElementBySelector(driver, By.xpath("//a[contains(text(),'Изменить условия подбора')]"));
        changeRequestButton.click();

        var selectTime = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"js-popup-request-player\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div"));
        selectTime.click();
        Random rand = new Random();
        int randomNum = rand.nextInt(8) + 1;

        var oneTime = Utils.getElementBySelector(driver, By.xpath(String.format("//*[@id=\"mCSB_20\"]/div[1]/ul/li[%d]/span", randomNum)));
        oneTime.click();
        var getClan = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"js-popup-request-player\"]/div/div[3]/button"));
        getClan.click();
    }

    public LoginPage toLoginPage() {
        var loginButton = Utils.getElementBySelector(driver, By.xpath("//a[contains(text(),'Войти')]"));
        loginButton.click();

        return new LoginPage(this.driver);
    }

    @SneakyThrows
    public void searchClan() {
        var inputClanName = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[4]/div[2]/article/div/header/form/div/div/div/input[1]"));
        inputClanName.click();
        inputClanName.sendKeys("ITMO");

        var searchButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[4]/div[2]/article/div/header/form/div/div/input"));
        searchButton.click();
        Thread.sleep(5000);
        changeTab();
    }

    @SneakyThrows
    private void changeTab() {
        String originalTab = driver.getWindowHandle();

        // Переключаемся на новую вкладку
        Set<String> allTabs = driver.getWindowHandles();
        for(String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        Thread.sleep(5000);
    }
}
