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



public class GetStartedScreen {

    public Printer log = new Printer(GetStartedScreen.class);
    private AppiumDriver driver;
    Utils utils;

    public GetStartedScreen(AppiumDriver driver) {
        this.utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Get Started']")
    public WebElement getStartedButton;

    public void clickGetStartedButton() {
        utils.waitUntilDisplayed(getStartedButton);
        utils.waitUntilClickable(getStartedButton);
        utils.clickElementUntil(getStartedButton);
        log.info("Clicked on the getStartedButton on the GetStartedScreen");
    }

}
