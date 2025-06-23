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
          def props = readFile "src/test/resources/test.properties"
          props = props.replace("apikey={your.accuweather.api.key}", "apikey=${env.APIKEY}")
          props = props.replace("email-application-password={application.password}", "email-application-password=${env.EMAILPW}")
          props = props.replace("sender-email={sender.email}", "sender-email=${env.SENDER}")
          props = props.replace("receiver-email={receiver.email}", "receiver-email=${env.RECEIVER}")
          writeFile file: "src/test/resources/test.properties", text: props
        }
      }
    }
    stage("Debug props") {
      steps {
        sh 'cat src/test/resources/test.properties'
      }
    }
    stage("Debug: List Files") {
      steps {
        sh "ls -l target/site/"
      }
    }
    stage("Run Tests") {
      steps {
        sh "mvn clean surefire-report:report"
      }
    }
  }
}
