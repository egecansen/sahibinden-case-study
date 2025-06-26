package app.screens;

import app.common.ScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LocationPermissionsScreen extends ScreenObject {


    public LocationPermissionsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']")
    public WebElement onlyThisTimeButton;


    public void clickLocationAndPermissionsNextButton() {
        utils.waitUntilDisplayed(nextButton);
        utils.waitUntilClickable(nextButton);
        utils.clickElementUntilTimeout(nextButton);
        log.info("Clicked on the nextButton on the LocationPermissionsScreen");

        utils.waitUntilDisplayed(onlyThisTimeButton);
        utils.clickElementUntilTimeout(onlyThisTimeButton);
        log.info("Clicked on the onlyThisTimeButton on the LocationPermissionsScreen");
    }


}
