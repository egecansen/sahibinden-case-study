package app.screens;

import app.common.ScreenObject;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LocationsDropdownScreen extends ScreenObject {

    public LocationsDropdownScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText/android.view.View")
    public WebElement searchBar;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    public WebElement searchInput;

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]")
    public WebElement firstResult;

    public void searchTargetLocation() {
        String targetLocation = ContextStore.get("location");
        utils.waitUntilDisplayed(searchBar);
        utils.waitUntilClickable(searchBar);
        utils.clickElementUntilTimeout(searchBar);

        log.info("Filling the search input with target location: " + targetLocation);
        searchInput.sendKeys(targetLocation);

        utils.waitUntilDisplayed(firstResult);
        log.info("Results are displayed");
        utils.waitUntilClickable(firstResult);
        utils.clickElementUntilTimeout(firstResult);
        log.info("Clicked on the firstResult on the LocationsDropdownScreen");
    }
}
