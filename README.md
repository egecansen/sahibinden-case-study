# Overview

This project is a modern, modular automation testing framework built with JUnit 5, Appium, and Spring Boot. Designed to support mobile automation for Android and iOS applications, the framework provides a scalable foundation for Test Automation as a Service (TaaS).

## Features

- **Appium** integration for mobile automation.
- **JUnit 5**for test execution and tagging.
- **Spring Boot** for configuration, modular design.
- Reporting with **Maven Surefire**.
- Simple email notification with HTML test reports (optional).
- Designed for easy integration with **Jenkins** and **Docker**.

## Prerequisites

- Java 17
- Maven 3.5.x
- Appium 2.x 
- JUnit 5
- Spring Boot 3.x
- Selenium 4.25.x


## Getting Started

Clone the repository:

```bash
git clone https://github.com/your-org/your-mobile-automation-project.git
cd your-mobile-automation-project
```


Build the project:

```bash
mvn clean install
```

Run

```bash
mvn clean test
```

To Run with Reporting

```bash
mvn clean surefire-report:report 
```

_Reports can be found at target/site/surefire-report.html_

## Configuration

_application.properties_

    appium.platformName=Android
    appium.automationName=uiautomator2
    appium.appWaitActivity=*
    appium.adbExecTimeout=180000
    appium.appWaitDuration=180000
    appium.androidInstallTimeout=180000
    appium.uiautomator2ServerLaunchTimeout=180000
    appium.uiautomator2ServerInstallTimeout=180000
    appium.apk=src/test/resources/apks/AccuWeather.apk


_test.properties_

    appiumLogLevel=warn

    apikey={your.accuweather.api.key}
    location=newyork

    use-default-device=true
    default-udid=emulator-5554
    default-device-name=DefaultDevice01
    default-port=4723
    default-address=127.0.0.1

    use-remote-appium=false
    default-remote-udid=host.docker.internal:5555

    send-report-email=false
    keep-email-logs=false
    email-host=smtp.gmail.com
    sender-email={sender.email}
    receiver-email={receiver.email}
    email-application-password={application.password}

    
_junit-platform.properties_

    junit.jupiter.execution.parallel.enabled = false
    junit.jupiter.execution.parallel.mode.default = concurrent
    junit.jupiter.execution.parallel.config.strategy = fixed
    junit.jupiter.execution.parallel.config.fixed.parallelism = 2


## Writing Tests

Write your test scenarios in Java using JUnit 5 (@Test, @Tag, etc).
Modularize logic with screen classes and step classes for maintainability.
Dependency injection is handled via Spring’s @Autowired components.

## Reporting & Notifications

Reports are generated via Maven Surefire (target/site/surefire-report.html).
The HTML report can be emailed automatically by providing credentials and setting the send-report-email property to true.
Continuous Integration

This framework is ready for CI/CD:

Integrate with Jenkins or similar tools using Docker Compose.
Test reports can be attached to build results or emailed.
