pipeline {
  agent any

  environment {
    APIKEY  = credentials("accuweather-apikey")
    EMAILPW = credentials("gmail-app-password")
    SENDER  = credentials("sender-email")
    RECEIVER = credentials("receiver-email")
  }

  stages {
    stage("Download APK") {
      steps {
        sh '''
          mkdir -p src/test/resources/apks
          gdown "https://drive.google.com/uc?id=1KWK34l1I9OxdaSwk5m3IZmn90tn0g1TF" -O src/test/resources/apks/AccuWeather.apk
        '''
      }
    }
    stage("Inject secrets into properties") {
      steps {
        script {
          def emailProps = readFile "post-report/src/main/resources/email.properties"
          def testProps = readFile "tests/src/main/resources/test.properties"
          props = testProps.replace("apikey={your.accuweather.api.key}", "apikey=${env.APIKEY}")
          props = emailProps.replace("email-application-password={application.password}", "email-application-password=${env.EMAILPW}")
          props = emailProps.replace("sender-email={sender.email}", "sender-email=${env.SENDER}")
          props = emailProps.replace("receiver-email={receiver.email}", "receiver-email=${env.RECEIVER}")
          writeFile file: "post-report/src/main/resources/email.properties", text: emailProps
          writeFile file: "tests/src/main/resources/test.properties", text: testProps
        }
      }
    }
    stage("Log test properties") {
      steps {
        sh 'cat src/test/resources/test.properties'
      }
    }
    stage("Log email properties") {
      steps {
        sh 'cat post-report/src/main/resources/email.properties'
      }
    }

    stage("Run Tests") {
      steps {
        sh "mvn clean test"
      }
    }
    stage("Generate reports and run post-reports") {
        steps {
            dir("tests") {
                sh "mvn surefire-report:report"
            }
            dir("post-report") {
                sh "mvn clean install exec:java"
            }
        }
    }
}
