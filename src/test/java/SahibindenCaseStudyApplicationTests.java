import app.api.steps.AccuWeatherSteps;
import app.common.ObjectInitializer;
import app.common.Utils;
import app.screens.factory.ObjectFactory;
import base.BaseTest;
import base.StatusWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

public class SahibindenCaseStudyApplicationTests extends BaseTest {


	@Autowired
	protected ObjectFactory factory;
	@Autowired
	private AccuWeatherSteps accuWeatherSteps;

	@Test @Tag("Case1") @DisplayName("Verify the Forecast data between UI screen and API response")
	public void foreCastFlow() {
		factory.homeScreen().basic();

		factory.getStartedScreen().clickGetStartedButton();
		factory.termsAndPrivacyScreen().clickAgreeAndContinueButton();
		factory.locationPermissionsScreen().clickLocationAndPermissionsNextButton();
		factory.personalizedAdsScreen().clickPersonalizedAdsScreenNextButton();
		factory.notificationsScreen().clickNotificationsNextButton();
		factory.homeScreen().verifyLandedOnHomeScreen();
		factory.homeScreen().clickOnLocationsFromMainNavigation();
		factory.locationsDropdownScreen().searchTargetLocation();
		factory.weatherInfoScreen().swipeUntilWeatherForecastBoxDisplayed();
		accuWeatherSteps.saveFiveDayForecastDataToContext();
		factory.weatherInfoScreen().verifyTheForecastData();

	}

	@Test @Tag("Case2") @DisplayName("Verify the Current Conditions data between UI screen and API response")
	public void currentConditionsFlow() {
		factory.getStartedScreen().clickGetStartedButton();
		factory.termsAndPrivacyScreen().clickAgreeAndContinueButton();
		factory.locationPermissionsScreen().clickLocationAndPermissionsNextButton();
		factory.personalizedAdsScreen().clickPersonalizedAdsScreenNextButton();
		factory.notificationsScreen().clickNotificationsNextButton();
		factory.homeScreen().verifyLandedOnHomeScreen();
		factory.homeScreen().clickOnLocationsFromMainNavigation();
		factory.locationsDropdownScreen().searchTargetLocation();
		factory.weatherInfoScreen().swipeUntilCurrentConditionsSeeMoreButtonDisplayed();
		factory.weatherInfoScreen().clickOnSeeMoreButton();
		accuWeatherSteps.saveTargetLocationWeatherInfoToContext();
		factory.currentConditionsScreen().verifyWeatherText();
		factory.currentConditionsScreen().verifyTemperatureValue();

	}

}
