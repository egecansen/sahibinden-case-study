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
          gdown "https://drive.google.com/uc?id=1KWK34l1I9OxdaSwk5m3IZmn90tn0g1TF" -O tests/src/test/resources/apks/AccuWeather.apk
        '''
      }
    }

    stage("Inject test secrets into properties") {
      steps {
        dir('tests') {
          script {
            def testProps = readFile('src/test/resources/test.properties')
            testProps = testProps.replace("apikey={your.accuweather.api.key}", "apikey=${env.APIKEY}")
            writeFile file: 'test.properties', text: testProps
          }
        }
      }
    }

    stage("Inject email secrets into properties") {
          steps {
            dir('post-report') {
              script {
                def emailProps = readFile('src/main/resources/email.properties')
                emailProps = emailProps.replace("email-application-password={application.password}", "email-application-password=${env.EMAILPW}")
                emailProps = emailProps.replace("sender-email={sender.email}", "sender-email=${env.SENDER}")
                emailProps = emailProps.replace("receiver-email={receiver.email}", "receiver-email=${env.RECEIVER}")
                writeFile file: 'email.properties', text: emailProps
              }
            }
          }
        }

    stage("Log test properties") {
      steps {
        sh 'cat tests/src/test/resources/test.properties'
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
        sh "mvn surefire-report:report -DskipTests || true"
      }
      dir("post-report") {
        sh "mvn clean install exec:java || true"
      }
    }
  }
}
