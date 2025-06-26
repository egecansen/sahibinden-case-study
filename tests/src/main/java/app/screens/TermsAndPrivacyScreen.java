package app.screens;

import app.common.ScreenObject;
import app.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Printer;


public class TermsAndPrivacyScreen extends ScreenObject {

    public TermsAndPrivacyScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='on_boarding_cta']")
    public WebElement agreeAndContinueButton;


    public void clickAgreeAndContinueButton() {
        utils.waitUntilClickable(agreeAndContinueButton);
        utils.clickElementUntilTimeout(agreeAndContinueButton);
        log.info("Clicked on the agreeAndContinueButton on the TermsAndPrivacyScreen");
    }

}
