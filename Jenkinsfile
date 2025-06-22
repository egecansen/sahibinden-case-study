pipeline {
  agent any
  stages {
    stage('Download APK') {
      steps {
        sh '''
          mkdir -p src/test/resources/apks
          gdown "https://drive.google.com/uc?id=1KWK34l1I9OxdaSwk5m3IZmn90tn0g1TF" -O src/test/resources/apks/AccuWeather.apk
        '''
      }
    }
    stage('Run Tests') {
      steps {
        sh 'mvn clean test'
      }
    }
  }
}
