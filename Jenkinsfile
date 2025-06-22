pipeline {
  agent any
  stages {
    stage('Download APK') {
      steps {
        sh '''
          mkdir -p src/test/resources/apks
          curl -L -o src/test/resources/apks/AccuWeather.apk "https://drive.google.com/uc?export=download&id=1KWK34l1I9OxdaSwk5m3IZmn90tn0g1TF"
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
