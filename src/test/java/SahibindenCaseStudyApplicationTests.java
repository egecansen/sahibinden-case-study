import app.api.steps.AccuWeatherSteps;
import app.common.ObjectInitializer;
import app.common.Utils;
import base.BaseTest;
import base.StatusWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(StatusWatcher.class)
public class SahibindenCaseStudyApplicationTests extends BaseTest {

	@Autowired
	private Utils utils;
	@Autowired
	protected ObjectInitializer init;
	@Autowired
	private AccuWeatherSteps accuWeatherSteps;

	@BeforeEach
	public void init() {
		init.initializeScreens(driver);
	}

	@Test @Tag("Case1") @DisplayName("Verify the Forecast data between UI screen and API response")
	public void foreCastFlow() {
		init.getGetStartedScreen().clickGetStartedButton();
		init.getTermsAndPrivacyScreen().clickAgreeAndContinueButton();
		init.getLocationPermissionsScreen().clickLocationAndPermissionsNextButton();
		init.getPersonalizedAdsScreen().clickPersonalizedAdsScreenNextButton();
		init.getNotificationsScreen().clickNotificationsNextButton();
		init.getHomeScreen().verifyLandedOnHomeScreen();
		init.getHomeScreen().clickOnLocationsFromMainNavigation();
		init.getLocationsDropdownScreen().searchTargetLocation();
		init.getWeatherInfoScreen().swipeUntilWeatherForecastBoxDisplayed();
		accuWeatherSteps.saveFiveDayForecastDataToContext();
		init.getWeatherInfoScreen().verifyTheForecastData();
		utils.waitFor(5);
	}

	@Test @Tag("Case2") @DisplayName("Verify the Current Conditions data between UI screen and API response")
	public void currentConditionsFlow() {
		init.getGetStartedScreen().clickGetStartedButton();
		init.getTermsAndPrivacyScreen().clickAgreeAndContinueButton();
		init.getLocationPermissionsScreen().clickLocationAndPermissionsNextButton();
		init.getPersonalizedAdsScreen().clickPersonalizedAdsScreenNextButton();
		init.getNotificationsScreen().clickNotificationsNextButton();
		init.getHomeScreen().verifyLandedOnHomeScreen();
		init.getHomeScreen().clickOnLocationsFromMainNavigation();
		init.getLocationsDropdownScreen().searchTargetLocation();
		init.getWeatherInfoScreen().swipeUntilCurrentConditionsDisplayed();
		init.getWeatherInfoScreen().clickOnSeeMoreButton();
		accuWeatherSteps.saveTargetLocationWeatherInfoToContext();
		init.getCurrentConditionsScreen().verifyWeatherText();
		init.getCurrentConditionsScreen().verifyTemperatureValue();
		utils.waitFor(5);
	}

}
