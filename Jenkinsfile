node('windows-host') {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv('my-sonarqube') {
      sh "ls"
      sh "cd ./apex-airline-system/backend"
      sh "./gradlew sonar"
    }
  }
}
