pipeline {
  agent any

  environment {
    APIKEY   = credentials("accuweather-apikey")
    EMAILPW  = credentials("gmail-app-password")
    SENDER   = credentials("sender-email")
    RECEIVER = credentials("receiver-email")
  }

  stages {
    stage("Download APK") {
      steps {
        sh '''
          mkdir -p tests/src/test/resources/apks
          gdown "https://drive.google.com/uc?id=1KWK34l1I9OxdaSwk5m3IZmn90tn0g1TF" -O src/test/resources/apks/AccuWeather.apk
        '''
      }
    }
    stage("Inject secrets into properties") {
      steps {
        script {
          def emailProps = readFile("post-report/src/main/resources/email.properties")
          def testProps = readFile("tests/src/main/resources/test.properties")

          testProps = testProps.replace("apikey={your.accuweather.api.key}", "apikey=${env.APIKEY}")
          emailProps = emailProps.replace("email-application-password={application.password}", "email-application-password=${env.EMAILPW}")
          emailProps = emailProps.replace("sender-email={sender.email}", "sender-email=${env.SENDER}")
          emailProps = emailProps.replace("receiver-email={receiver.email}", "receiver-email=${env.RECEIVER}")

          writeFile file: "post-report/src/main/resources/email.properties", text: emailProps
          writeFile file: "tests/src/main/resources/test.properties", text: testProps
        }
      }
    }
    stage("Log test properties") {
      steps {
        sh 'cat tests/src/main/resources/test.properties'
      }
    }
    stage("Log email properties") {
      steps {
        sh 'cat post-report/src/main/resources/email.properties'
      }
    }
    stage("Run Tests") {
      steps {
        dir("tests") {
          sh "mvn clean test"
        }
      }
    }
  }

  post {
    always {
      // These always run, even if a previous stage fails
      dir("tests") {
        sh "mvn surefire-report:report || true" // use '|| true' to avoid stopping on error
      }
      dir("post-report") {
        sh "mvn clean install exec:java || true"
      }
    }
  }
}
