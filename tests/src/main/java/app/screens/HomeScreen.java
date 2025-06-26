package app.screens;

import app.common.ScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomeScreen extends ScreenObject {

    public HomeScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Local Affiliate logo']")
    public WebElement mainNavigationLogo;

    public void verifyLandedOnHomeScreen() {
        utils.waitUntilDisplayed(mainNavigationLogo);
        log.success("mainNavigationLogo is displayed on the HomeScreen");
    }

    public void clickOnLocationsFromMainNavigation() {
        utils.clickElementUntilTimeout(mainNavigationLogo);
        log.info("Clicked mainNavigationLogo on the HomeScreen");
    }

}
