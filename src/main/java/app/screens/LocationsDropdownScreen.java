package app.screens;

import app.common.Utils;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Printer;


@Component
public class LocationsDropdownScreen extends Utils {

    public Printer log = new Printer(LocationsDropdownScreen.class);

    public void init(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText/android.view.View")
    public WebElement searchBar;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    public WebElement searchInput;

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]")
    public WebElement firstResult;

    public void searchTargetLocation() {
        String targetLocation = ContextStore.get("location");
        waitUntilDisplayed(searchBar);
        waitUntilClickable(searchBar);
        clickElementUntil(searchBar);

        log.info("Filling the search input with target location: " + targetLocation);
        searchInput.sendKeys(targetLocation);

        waitUntilDisplayed(firstResult);
        log.info("Results are displayed");
        waitUntilClickable(firstResult);
        clickElementUntil(firstResult);
        log.info("Clicked on the firstResult on the LocationsDropdownScreen");
    }
}
