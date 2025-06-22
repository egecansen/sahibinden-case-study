package app.screens;

import app.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Printer;


@Component
public class LocationPermissionsScreen extends Utils {

    public Printer log = new Printer(LocationPermissionsScreen.class);

    public void init(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_one_time_button']")
    public WebElement onlyThisTimeButton;


    public void clickLocationAndPermissionsNextButton() {
        waitUntilDisplayed(nextButton);
        waitUntilClickable(nextButton);
        clickElementUntil(nextButton);
        log.info("Clicked on the nextButton on the LocationPermissionsScreen");

        waitUntilDisplayed(onlyThisTimeButton);
        clickElementUntil(onlyThisTimeButton);
        log.info("Clicked on the onlyThisTimeButton on the LocationPermissionsScreen");
    }


}
