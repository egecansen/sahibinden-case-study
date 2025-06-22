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
public class GetStartedScreen extends Utils {

    public Printer log = new Printer(GetStartedScreen.class);

    public void init(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Get Started']")
    public WebElement getStartedButton;

    public void clickGetStartedButton() {
        waitUntilDisplayed(getStartedButton);
        waitUntilClickable(getStartedButton);
        clickElementUntil(getStartedButton);
        log.info("Clicked on the getStartedButton on the GetStartedScreen");
    }

}
