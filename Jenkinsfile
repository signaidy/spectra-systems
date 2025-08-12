pipeline {
  agent any
  options { timestamps() }

  environment {
    APP_DIR = 'nexus-agency-system/backend'
  }

  stages {
    stage('Select Environment') {
      steps {
        script {
          if (env.BRANCH_NAME == 'dev') {
            env.DEPLOY_ENV = 'dev'
            env.COMPOSE = 'docker-compose.dev.yml'
            env.TAG = 'dev'
          } else if (env.BRANCH_NAME == 'uat') {
            env.DEPLOY_ENV = 'uat'
            env.COMPOSE = 'docker-compose.uat.yml'
            env.TAG = 'uat'
          } else if (env.BRANCH_NAME == 'master') {
            env.DEPLOY_ENV = 'prod'
            env.COMPOSE = 'docker-compose.prod.yml'
            env.TAG = 'prod'
          } else {
            error("Branch ${env.BRANCH_NAME} not deployable")
          }
        }
      }
    }

    stage('Checkout') {
      steps {
        checkout scm
        dir("${APP_DIR}") {
          script {
            if (isUnix()) {
              sh 'ls -la'
            } else {
              bat 'dir'
            }
          }
        }
      }
    }

    stage('Build App (Gradle)') {
      steps {
        dir("${APP_DIR}") {
          script {
            if (isUnix()) {
              sh './gradlew clean build -x test'
            } else {
              bat 'gradlew.bat clean build -x test'
            }
          }
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        dir("${APP_DIR}") {
          script {
            if (isUnix()) {
              sh "docker build -t nexus-app:${TAG} ."
            } else {
              bat "docker build -t nexus-app:%TAG% ."
            }
          }
        }
      }
    }

    stage('Deploy with Docker Compose') {
      steps {
        dir("${APP_DIR}") {
          script {
            if (isUnix()) {
              sh "docker compose -f ${COMPOSE} up -d --build"
              sh "docker image prune -f"
            } else {
              bat "docker compose -f %COMPOSE% up -d --build"
              bat "docker image prune -f"
            }
          }
        }
      }
    }
  }

  post {
    success {
      emailext(
        subject: "[Deploy OK] Nexus -> ${env.DEPLOY_ENV}",
        to: 'lead.dev@your-domain,product.owner@your-domain',
        body: "Deployment succeeded on ${env.DEPLOY_ENV}.\nBuild: ${env.BUILD_URL}"
      )
    }
    failure {
      emailext(
        subject: "[Deploy FAILED] Nexus -> ${env.DEPLOY_ENV}",
        to: 'lead.dev@your-domain,product.owner@your-domain',
        body: "Deployment FAILED on ${env.DEPLOY_ENV}.\nBuild: ${env.BUILD_URL}"
      )
    }
  }
}
