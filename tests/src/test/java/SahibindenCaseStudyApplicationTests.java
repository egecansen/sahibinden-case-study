import app.api.steps.AccuWeatherSteps;
import app.screens.factory.ObjectFactory;
import base.BaseTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

public class SahibindenCaseStudyApplicationTests extends BaseTest {


	@Autowired
	protected ObjectFactory factory;
	@Autowired
	private AccuWeatherSteps accuWeatherSteps;


	@Test @Tag("Case1") @DisplayName("Verify the Forecast data between UI screen and API response")
	public void foreCastFlow() {
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
