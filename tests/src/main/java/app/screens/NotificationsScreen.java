package app.screens;

import app.common.ScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class NotificationsScreen extends ScreenObject {

    public NotificationsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']")
    public WebElement allowNotificationsButton;


    public void clickNotificationsNextButton() {
        utils.waitUntilClickable(nextButton);
        utils.clickElementUntilTimeout(nextButton);
        log.info("Clicked on the nextButton on the NotificationsScreen");

        utils.waitUntilDisplayed(allowNotificationsButton);
        utils.waitUntilClickable(allowNotificationsButton);
        utils.clickElementUntilTimeout(allowNotificationsButton);
        log.info("Clicked on the allowNotificationsButton on the NotificationsScreen");
    }
}
