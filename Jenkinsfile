pipeline {
  agent {
    docker {
      

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
