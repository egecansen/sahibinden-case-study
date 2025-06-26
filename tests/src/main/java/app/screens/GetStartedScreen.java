package app.screens;

import app.common.ScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GetStartedScreen extends ScreenObject {

    public GetStartedScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Get Started']")
    public WebElement getStartedButton;

    public void clickGetStartedButton() {
        utils.waitUntilDisplayed(getStartedButton);
        utils.waitUntilClickable(getStartedButton);
        utils.clickElementUntilTimeout(getStartedButton);
        log.info("Clicked on the getStartedButton on the GetStartedScreen");
    }

}
