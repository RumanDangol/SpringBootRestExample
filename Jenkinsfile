pipeline {
  agent any

  
  stages {
    stage('Develop') {
      steps {
        echo 'Developing..'
      }
    }
    stage('Stage') {
      steps {
        echo 'Staging..'
      }
    }
    stage('Prod') {
      steps {
        echo 'Production....'
      }
    }
    stage('Example') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
  }
}
