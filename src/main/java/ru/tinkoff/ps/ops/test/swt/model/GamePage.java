package ru.tinkoff.ps.ops.test.swt.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.ps.ops.test.swt.Utils;

public class GamePage extends Page{
    public GamePage(WebDriver driver) {
        super(driver);
    }

    public void downloadGame() {
        var downloadButton = Utils.getElementBySelector(driver, By.xpath("//body/div[1]/div[1]/div[4]/div[1]/div[1]/div[3]/div[2]/a[1]"));
        downloadButton.click();
    }
}
