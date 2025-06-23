# Overview

This project is a automation testing framework built with JUnit 5, Appium, and Spring Boot, designed to support mobile automation.

## Features

- **Appium** for mobile automation.
- **JUnit 5** for test execution.
- **Spring Boot** for configuration.
- Reporting with **Maven Surefire**.
- Email notification with HTML test reports (optional).
- **Jenkins** and **Docker** for CI/CD.

## Prerequisites

- Java 17
- Maven 3.5.x
- Appium 2.x 
- JUnit 5
- Spring Boot 3.x
- Selenium 4.25.x
- Docker (optional for CI/CD)


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

To Run with reporting

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


## Reporting & Notifications

Reports are generated via Maven Surefire (target/site/surefire-report.html).
The HTML report can be emailed by providing credentials and setting the send-report-email property to true.

## CI/CD

This project is designed for CI/CD integration using Jenkins and Docker. All dependencies are installed inside the Jenkins container automatically.

  
1. Start Jenkins & Appium with Docker

```bash
docker compose up -d
```
_This will build and start both Jenkins and Appium in containers configured to work together._
_If your Docker install uses the old CLI, use docker-compose up -d instead._

- Jenkins UI: http://localhost:8088
- Appium server: localhost:4723

**Device Connection**

This project includes custom entrypoint scripts that automatically connect local Android emulator to Appium and Jenkins.

entrypoint.sh: Starts the ADB server and connects to your emulator before starting Jenkins.
appiumentrypoint.sh: Does the same before starting Appium.
The docker-compose.yaml sets up both containers with these scripts, and shares network access.
How to use:

_Make sure your Android emulator is running and listening for TCP connections._

2. Jenkins Initial Setup
   
On first launch, Jenkins will ask for an admin password, find it in jenkins_home/secrets/initialAdminPassword.
Simply copy & paste this command to your terminal:

```bash
docker exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Follow the UI prompts to install plugins and set up an admin user.

4. Run Your Tests
Trigger builds via Jenkins UI or webhooks for automated CI/CD.
Test results and reports will be generated and can be viewed or emailed according to your configuration.
