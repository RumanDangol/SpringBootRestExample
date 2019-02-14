pipeline {
  agent {
    dockerfile {
      filename 'Dockerfile'
    }

  }
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
    }
}
