package app.screens;

import app.common.ScreenObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PersonalizedAdsScreen extends ScreenObject {


    public PersonalizedAdsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Personalized Ads']")
    public WebElement allowPersonalizedAdsButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Close tab']")
    public WebElement closePageButton;


    public void clickPersonalizedAdsScreenNextButton() {
        utils.waitUntilClickable(nextButton);
        utils.clickElementUntilTimeout(nextButton);
        log.info("Clicked on the nextButton on the PersonalizedAdsScreen");

        closeTermsOfUsePageIfDisplayed();
        utils.waitUntilDisplayed(allowPersonalizedAdsButton);
        utils.waitUntilClickable(allowPersonalizedAdsButton);
        utils.clickElementUntilTimeout(allowPersonalizedAdsButton);
        log.info("Clicked on the allowPersonalizedAdsButton on the PersonalizedAdsScreen");
    }


    public void closeTermsOfUsePageIfDisplayed() {
        try {
            if (closePageButton.isDisplayed()) {
                log.info("Terms of Use page is displayed");
                log.info("Closing the Terms of Use page...");
                utils.clickElementUntilTimeout(closePageButton);
            }
        }
        catch (NoSuchElementException ignored) {}
    }

}
