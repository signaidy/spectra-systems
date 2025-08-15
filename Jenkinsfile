pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
  }

  environment {
    BACKEND_DIR   = "nexus-agency-system/backend"
    FRONTEND_DIR  = "nexus-agency-system/frontend"

    DEPLOY_COMPOSE = "ci/compose.deploy.yml"
    DEPLOY_ENVFILE = ".env.deploy"

    DOCKER_HOST = "tcp://dind:2375"

    ORACLE_HOST = "host.docker.internal"
    ORACLE_PORT = "1521"
    ORACLE_SVC  = "FREEPDB1"
    ORACLE_DIALECT = "org.hibernate.dialect.OracleDialect"

    JWT_DEV  = "super-secret-dev"
    JWT_UAT  = "super-secret-uat"
    JWT_PROD = "super-secret-prod"
  }

  triggers {
    pollSCM('')
  }

  stages {

    stage('Checkout') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        script {
          def scmVars = checkout scm     // <- returns a map with GIT_COMMIT, etc.
          env.GIT_COMMIT = scmVars.GIT_COMMIT
          env.SHORT_SHA  = (env.GIT_COMMIT ?: 'unknown').take(7)
          echo "Building branch: ${env.BRANCH_NAME} @ ${env.SHORT_SHA}"
        }
      }
    }

    stage('Set env by branch') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        script {
          if (env.BRANCH_NAME == 'dev') {
            env.SPRING_PROFILE = 'dev'
            env.DB_USER = 'nexus_dev'
            env.DB_PASS = 'nexus_dev'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8081'
            env.FRONTEND_PORT = '3001'
            env.BACKEND_PORT  = '8081'
          } else if (env.BRANCH_NAME == 'uat') {
            env.SPRING_PROFILE = 'uat'
            env.DB_USER = 'nexus_uat'
            env.DB_PASS = 'nexus_uat'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8082'
            env.FRONTEND_PORT = '3002'
            env.BACKEND_PORT  = '8082'
          } else { // main
            env.SPRING_PROFILE = 'prod'
            env.DB_USER = 'nexus_prod'
            env.DB_PASS = 'nexus_prod'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8083'
            env.FRONTEND_PORT = '3003'
            env.BACKEND_PORT  = '8083'
          }

          env.DB_URL = "jdbc:oracle:thin:@${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SVC}"
          env.JWT_SECRET = (env.SPRING_PROFILE == 'prod' ? JWT_PROD :
                            env.SPRING_PROFILE == 'uat'  ? JWT_UAT  : JWT_DEV)

          echo "Profile: ${env.SPRING_PROFILE}"
          echo "DB_URL : ${env.DB_URL}"
          echo "Schema : ${env.DB_USER}"
          echo "Backend Port: ${env.BACKEND_PORT}"
          echo "Frontend Port: ${env.FRONTEND_PORT}"
          echo "Public Backend URL for FE: ${env.PUBLIC_BACKEND_URL}"
        }
      }
    }

    stage('Build Backend (Gradle)') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        dir("${BACKEND_DIR}") {
          sh """
            ./gradlew --version || chmod +x ./gradlew
            ./gradlew clean build -x test
          """
        }
      }
    }

    stage('Build Frontend (Docker build on DinD)') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        dir("${FRONTEND_DIR}") {
          sh """
            docker build \
              --build-arg PUBLIC_BACKEND_URL='${PUBLIC_BACKEND_URL}' \
              -t local/spectra-frontend:${BRANCH_NAME}-${GIT_COMMIT.take(7)} \
              -f Dockerfile .
          """
        }
      }
    }

    stage('Prepare .env.deploy') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        writeFile file: "${DEPLOY_ENVFILE}", text: """
ORACLE_HOST=${ORACLE_HOST}
ORACLE_PORT=${ORACLE_PORT}
ORACLE_SVC=${ORACLE_SVC}

SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}
DB_URL=${DB_URL}
DB_USER=${DB_USER}
DB_PASS=${DB_PASS}
JWT_SECRET=${JWT_SECRET}
SPRING_JPA_DIALECT=${ORACLE_DIALECT}

BACKEND_PORT=${BACKEND_PORT}
FRONTEND_PORT=${FRONTEND_PORT}

PUBLIC_BACKEND_URL=${PUBLIC_BACKEND_URL}
"""
        sh "cat ${DEPLOY_ENVFILE}"
      }
    }

    stage('Docker Info (DIND)') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        sh '''
          docker version
          docker info
        '''
      }
    }

    stage('Deploy (docker compose up via DIND)') {
      when {
        allOf {
          expression { return env.CHANGE_ID == null }
          anyOf { branch 'dev'; branch 'uat'; branch 'main' }
        }
      }
      steps {
        sh '''
          docker compose --env-file ${DEPLOY_ENVFILE} -f ${DEPLOY_COMPOSE} up -d --build
        '''
      }
    }
  }

  post {
    success { echo "Deployed ${env.BRANCH_NAME} OK" }
    failure { echo "Deployment failed for ${env.BRANCH_NAME}" }
  }
}