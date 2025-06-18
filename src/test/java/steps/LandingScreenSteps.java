package steps;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import app.screens.LandingScreen;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LandingScreenSteps extends BaseTest {

    @Test
    public void landingScreenLoads() {
        System.out.println(">>> Test: driver = " + driver);
        LandingScreen landingScreen = new LandingScreen(driver);
        assertTrue(landingScreen.isDisplayed(), "Get Started button should be visible");
    }
}
