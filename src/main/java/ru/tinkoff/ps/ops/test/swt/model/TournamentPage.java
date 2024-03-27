package ru.tinkoff.ps.ops.test.swt.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class TournamentPage extends Page {
    public TournamentPage(WebDriver driver) {
        super(driver);
    }

    public void takePartInTournamentJoinTeam() {
        var takePartButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"tournaments_list\"]/div[3]/div[1]/div[1]/div[3]/div[2]/div/a[1]"));
        takePartButton.click();

        var findTeamButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[1]/div/div/div[2]/div/div[1]/div/span[3]/a"));
        findTeamButton.click();
        for (int i = 2; i < 11; i++) {
            var joinButton = Utils.getElementBySelector(driver, By.xpath(String.format("//*[@id=\"registration_popup\"]/div[2]/div/div/div[2]/table/tbody/tr[%d]/td[6]/a/span", i)));
            joinButton.click();
            // check if you need password
            if (Utils.isElementPresent(driver, By.xpath("//*[@id=\"registration_popup\"]/div[2]/div/div/div[2]/table/tbody/tr[3]/td[2]/form/span[1]"))) {
                var declineButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[2]/div/div/div[2]/table/tbody/tr[3]/td[2]/form/a[1]"));
                declineButton.click();
            } else {
                Utils.waitUntilPageLoads(driver, 10);
                var leaveTeamButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"team_operations\"]/div/a[2]/span[2]"));
                leaveTeamButton.click();
                var leaveTeamConfirmButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"text_component\"]/div/div/div/div/div/footer/div/a[2]"));
                leaveTeamConfirmButton.click();
                break;
            }
        }
    }

    public void takePartInTournamentCreateTeam() {
        var takePartButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"tournaments_list\"]/div[3]/div[1]/div[1]/div[3]/div[2]/div/a[1]"));
        takePartButton.click();

        var createTeamButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[1]/div/div/div[2]/div/div[3]/div/span[3]/a"));
        createTeamButton.click();
        var teamNameInput = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[2]/div/div/div[2]/div[1]/form[2]/div/div[1]/div/input"));
        teamNameInput.sendKeys("ITMO");
        var contactInput = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[2]/div/div/div[2]/div[1]/form[2]/div/div[2]/div/input"));
        contactInput.sendKeys("university");
        var createTeamButton2 = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"registration_popup\"]/div[2]/div/div/div[3]/div/a"));
        createTeamButton2.click();
        Utils.waitUntilPageLoads(driver, 10);
        var disbandTeamButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"team_operations\"]/div/div/div[2]/a"));
        disbandTeamButton.click();
        var disbandTeamConfirmButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"text_component\"]/div/div/div/div/div/footer/div/a[2]"));
        disbandTeamConfirmButton.click();
    }
}
