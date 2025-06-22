package app.screens;

import app.common.Utils;
import app.driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Printer;

@Component
public class PersonalizedAdsScreen extends Utils {

    public Printer log = new Printer(PersonalizedAdsScreen.class);

    public void init(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    public WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Personalized Ads']")
    public WebElement allowPersonalizedAdsButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Close tab']")
    public WebElement closePageButton;


    public void clickPersonalizedAdsScreenNextButton() {
        waitUntilClickable(nextButton);
        clickElementUntil(nextButton);
        log.info("Clicked on the nextButton on the PersonalizedAdsScreen");

        waitUntilDisplayed(allowPersonalizedAdsButton);
        waitUntilClickable(allowPersonalizedAdsButton);
        clickElementUntil(allowPersonalizedAdsButton);
        log.info("Clicked on the allowPersonalizedAdsButton on the PersonalizedAdsScreen");
        closeTermsOfUsePageIfDisplayed();
    }


    public void closeTermsOfUsePageIfDisplayed() {
        try {
            if (closePageButton.isDisplayed()) {
                log.info("Terms of Use page is displayed");
                log.info("Closing the Terms of Use page...");
                clickElementUntil(closePageButton);
            }
        }
        catch (NoSuchElementException ignored) {}
    }

}
