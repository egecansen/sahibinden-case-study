package app.screens;

import app.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Printer;


public class PersonalizedAdsScreen {

    public Printer log = new Printer(PersonalizedAdsScreen.class);

    Utils utils;
    public PersonalizedAdsScreen(AppiumDriver driver) {
        utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Personalized Ads']")
    public WebElement allowPersonalizedAdsButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Close tab']")
    public WebElement closePageButton;


    public void clickPersonalizedAdsScreenNextButton() {
        utils.waitUntilClickable(nextButton);
        utils.clickElementUntil(nextButton);
        log.info("Clicked on the nextButton on the PersonalizedAdsScreen");

        closeTermsOfUsePageIfDisplayed();
        utils.waitUntilDisplayed(allowPersonalizedAdsButton);
        utils.waitUntilClickable(allowPersonalizedAdsButton);
        utils.clickElementUntil(allowPersonalizedAdsButton);
        log.info("Clicked on the allowPersonalizedAdsButton on the PersonalizedAdsScreen");
    }


    public void closeTermsOfUsePageIfDisplayed() {
        try {
            if (closePageButton.isDisplayed()) {
                log.info("Terms of Use page is displayed");
                log.info("Closing the Terms of Use page...");
                utils.clickElementUntil(closePageButton);
            }
        }
        catch (NoSuchElementException ignored) {}
    }

}
