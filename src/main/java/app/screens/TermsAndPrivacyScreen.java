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


public class TermsAndPrivacyScreen {

    public Printer log = new Printer(TermsAndPrivacyScreen.class);
    Utils utils;

    public TermsAndPrivacyScreen(AppiumDriver driver) {
        utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='on_boarding_cta']")
    public WebElement agreeAndContinueButton;


    public void clickAgreeAndContinueButton() {
        utils.waitUntilClickable(agreeAndContinueButton);
        utils.clickElementUntil(agreeAndContinueButton);
        log.info("Clicked on the agreeAndContinueButton on the TermsAndPrivacyScreen");
    }

}
