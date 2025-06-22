package app.common;

import app.common.enums.Direction;
import app.driver.Driver;
import app.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.NumericUtilities;
import utils.Printer;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static utils.StringUtilities.Color.BLUE;
import static utils.StringUtilities.highlighted;

@Component
@Scope("prototype")
public class Utils {

    private AppiumDriver driver;

    private final Printer log = new Printer(Utils.class);

    public AppiumDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver();
            if (driver == null) {
                throw new IllegalStateException("Appium driver is not initialized.");
            }
        }
        return driver;
    }

    public void waitFor(int duration) {
        try {
            TimeUnit.SECONDS.sleep(duration);
            log.info("Waited for " + duration + "seconds");
        }
        catch (InterruptedException ignored) {}
    }

    public void waitUntilAbsence(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>((WebDriver) getDriver())
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        try {
            log.info("Waiting the elements absence...");
            fluentWait.until(webDriver -> !element.isDisplayed());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void performSequence(Sequence sequence, long initialTime, AppiumDriver driver) {
        try {
            driver.perform(singletonList(sequence));
        } catch (WebDriverException exception) {
            if (!(System.currentTimeMillis() - initialTime > 15000)) {
                performSequence(sequence, initialTime, driver);
            } else throw exception;
        }
    }

    public void swipe(Point pointOfDeparture, Point pointOfArrival) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(), pointOfDeparture.x, pointOfDeparture.y)
        );
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        sequence.addAction(new Pause(finger, ofMillis(250)));
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(750),
                PointerInput.Origin.viewport(), pointOfArrival.x, pointOfArrival.y)
        );
        sequence.addAction(new Pause(finger, ofMillis(250)));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        performSequence(sequence, System.currentTimeMillis(), getDriver());
        sequence.addAction(new Pause(finger, ofMillis(250)));
    }

    public void swipeInDirection(Direction direction) {
        log.info("Swiping " + highlighted(BLUE, direction.name().toLowerCase()));
        Point center = new Point(
                getDriver().manage().window().getSize().getWidth() / 2,
                getDriver().manage().window().getSize().getHeight() / 2
        );

        Point destination = switch (direction) {
            case up -> new Point(
                    center.getX(),
                    center.getY() - (3 * (getDriver().manage().window().getSize().getHeight() / 5))
            );
            case down -> new Point(
                    center.getX(),
                    center.getY() + (3 * (getDriver().manage().window().getSize().getHeight() / 5))
            );
            case right -> new Point(
                    center.getX() - (3 * (getDriver().manage().window().getSize().getWidth() / 4)),
                    center.getY()
            );
            case left -> new Point(
                    center.getX() + (3 * (getDriver().manage().window().getSize().getWidth() / 4)),
                    center.getY()
            );
        };
        swipe(center, destination);
    }

    public void swipeUntilFound(WebElement element, Direction direction) {
        int duration = 30000;
        final long startTime = System.currentTimeMillis();
        log.info("Swiping until the element found");
        while (System.currentTimeMillis() - startTime < duration) {
            try {
               if (element.isDisplayed()){
                   swipeInDirection(direction);
                   break;
               }
                System.out.println("text: " + element.getText());
            } catch (NoSuchElementException e) {
                swipeInDirection(direction);
            }
        }
    }

    public void waitUntilDisplayed(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver());

        fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        try {
            log.info("Waiting the element to be displayed");
            fluentWait.until(webDriver -> element.isDisplayed());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
    }

    public void waitUntilClickable(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        log.info("Waiting the element to be clickable");

        fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElementUntil(WebElement element) {
        final long startTime = System.currentTimeMillis();
        boolean isClicked = false;
        int attemptCounter = 0;
        int duration = 30000;

        log.info("Clicking the element");
        while (System.currentTimeMillis() - startTime < duration) {
            try {
                element.click();
                isClicked = true;
                break;
            } catch (WebDriverException e) {
                attemptCounter++;
            }
        }
        if (!isClicked) {
            throw new RuntimeException("Could not click the element after " + attemptCounter + " attempts");
        }
    }

    public static void captureScreen(AppiumDriver driver, String screenshotName) {
        if (driver == null) {
            throw new RuntimeException("Driver is null!");
        }
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("src/test/resources/screenshots" + File.separator + screenshotName + "-" + NumericUtilities.randomNumber(1,1000) + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
