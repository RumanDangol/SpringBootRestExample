pipeline {
  agent {
    docker {
      image 'openjdk:8-jdk-alpine'
      args '-p 5000:5000'

    }

  }
  stages {
    stage('Develop') {
      steps {
        sh './gradlew build docker'
      }
    }
  }
}
