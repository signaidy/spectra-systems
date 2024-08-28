node('windows-host') {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "cd ./apex-airline-system/backend"
      sh "./gradlew sonar"
    }
  }
}