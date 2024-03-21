package ru.tinkoff.ps.ops.test.swt.model;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class ClanPage extends Page{
    public ClanPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public void createClan() {
        var createButton = Utils.getElementBySelector(driver, By.xpath("//header/div[1]/div[1]/a[2]"));
        createButton.click();

        Thread.sleep(10000);
        var inputName = driver.findElement(By.xpath("//input[@id='clan_name']"));

        inputName.clear();
        inputName.sendKeys("adsfasdfdf");

        var inputTag = Utils.getElementBySelector(driver, By.xpath("//input[@id='clan_tag']"));
        inputTag.clear();
        inputTag.sendKeys("FD212");
    }
}
