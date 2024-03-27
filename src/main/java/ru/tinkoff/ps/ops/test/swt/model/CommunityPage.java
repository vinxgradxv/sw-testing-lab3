package ru.tinkoff.ps.ops.test.swt.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class CommunityPage extends Page {
    public CommunityPage(WebDriver driver) {
        super(driver);
    }

    public void findPlayer() {
        var selectButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[2]/div/ul/li[6]/a/span"));
        selectButton.click();
        var findPlayerLink = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[2]/div/ul/li[6]/div/ul/li[2]/a"));
        findPlayerLink.click();
        var inputField = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"account_table_search\"]"));
        inputField.click();
        inputField.sendKeys("max");
        var searchButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"account_search_form\"]/div/div[2]/input"));
        searchButton.click();
    }

    public void inviteFriend() {
        var selectButton = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[2]/div/ul/li[6]/a/span"));
        selectButton.click();
        var inviteFriendLink = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[2]/div/ul/li[6]/div/ul/li[3]/a"));
        inviteFriendLink.click();
    }

    public void goToDiscord() {
        var discordBlock = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[1]/div[4]/div/div/div/div[2]/a"));
        discordBlock.click();
    }

    public void goToYoutube() {
        var youtubeBlock = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[1]/div[4]/div/div/div/div[3]/a"));
        youtubeBlock.click();
    }

    public void goToTwitch() {
        var twitchBlock = Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div[4]/div[2]/div[1]/div[4]/div/div/div/div[4]/a"));
        twitchBlock.click();
    }
}
