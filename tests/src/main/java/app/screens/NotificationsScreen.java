package app.screens;

import app.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Printer;


public class NotificationsScreen {

    public Printer log = new Printer(NotificationsScreen.class);
    Utils utils;

    public NotificationsScreen(AppiumDriver driver) {
        utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']")
    public WebElement allowNotificationsButton;


    public void clickNotificationsNextButton() {
        utils.waitUntilClickable(nextButton);
        utils.clickElementUntil(nextButton);
        log.info("Clicked on the nextButton on the NotificationsScreen");

        utils.waitUntilDisplayed(allowNotificationsButton);
        utils.waitUntilClickable(allowNotificationsButton);
        utils.clickElementUntil(allowNotificationsButton);
        log.info("Clicked on the allowNotificationsButton on the NotificationsScreen");
    }
}
