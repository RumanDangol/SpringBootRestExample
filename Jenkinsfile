pipeline {
  agent {
    docker {
      image 'openjdk:8-jdk-alpine'
      args '-p 8080:8080'

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