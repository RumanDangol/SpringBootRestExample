pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
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