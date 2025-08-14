pipeline {
  agent any
  options {
    timestamps()
    ansiColor('xterm')
  }
  environment {
    // Paths relativos en el repo
    BACKEND_DIR = "nexus-agency-system/backend"
    FRONTEND_DIR = "nexus-agency-system/frontend"

    // Docker Compose deploy file
    DEPLOY_COMPOSE = "ci/compose.deploy.yml"
    DEPLOY_ENVFILE = ".env.deploy"

    // Oracle compartido (ajusta host/puerto si no es local)
    ORACLE_HOST = "host.docker.internal"
    ORACLE_PORT = "1521"
    ORACLE_SVC  = "FREEPDB1"   // para gvenzl/oracle-free, el servicio por defecto suele ser FREEPDB1
    ORACLE_DIALECT = "org.hibernate.dialect.OracleDialect"

    // JWT por rama (simples por ahora)
    JWT_DEV  = "super-secret-dev"
    JWT_UAT  = "super-secret-uat"
    JWT_PROD = "super-secret-prod"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        script {
          echo "Building branch: ${env.BRANCH_NAME}"
        }
      }
    }

    stage('Set env by branch') {
      steps {
        script {
          // Mapea rama → perfil/usuarios/schema y URLs
          if (env.BRANCH_NAME == 'dev') {
            env.SPRING_PROFILE = 'dev'
            env.DB_USER = 'nexus_dev'
            env.DB_PASS = 'nexus_dev'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8081' // puerto que expondremos backend DEV
            env.FRONTEND_PORT = '3001'
            env.BACKEND_PORT  = '8081'
          } else if (env.BRANCH_NAME == 'uat') {
            env.SPRING_PROFILE = 'uat'
            env.DB_USER = 'nexus_uat'
            env.DB_PASS = 'nexus_uat'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8082'
            env.FRONTEND_PORT = '3002'
            env.BACKEND_PORT  = '8082'
          } else if (env.BRANCH_NAME == 'master') {
            env.SPRING_PROFILE = 'prod'
            env.DB_USER = 'nexus_prod'
            env.DB_PASS = 'nexus_prod'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8083'
            env.FRONTEND_PORT = '3003'
            env.BACKEND_PORT  = '8083'
          } else {
            // Si otra rama, trata como dev para pruebas
            env.SPRING_PROFILE = 'dev'
            env.DB_USER = 'nexus_dev'
            env.DB_PASS = 'nexus_dev'
            env.PUBLIC_BACKEND_URL = 'http://localhost:8081'
            env.FRONTEND_PORT = '3001'
            env.BACKEND_PORT  = '8081'
          }

          // Construye URL JDBC Oracle
          env.DB_URL = "jdbc:oracle:thin:@${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SVC}"

          // JWT por perfil simple
          env.JWT_SECRET =
            (env.SPRING_PROFILE == 'prod' ? JWT_PROD :
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
      steps {
        dir("${BACKEND_DIR}") {
          sh """
            ./gradlew --version || chmod +x ./gradlew
            ./gradlew clean build -x test
          """
        }
      }
    }

    stage('Build Frontend (Node)') {
      steps {
        dir("${FRONTEND_DIR}") {
          sh """
            echo "PWD on host:"
            pwd
            echo "Host listing:"
            ls -la

            docker -H tcp://dind:2375 run --rm \
              -v \$(pwd):/app -w /app \
              -e PUBLIC_BACKEND_URL='${PUBLIC_BACKEND_URL}' \
              --user 1000:1000 \
              node:20-bullseye bash -lc '
                set -e
                echo "PWD in container: \$PWD"
                echo "Container listing:"
                ls -la
                echo "Node & npm versions:"
                node -v && npm -v
                if [ -f package-lock.json ]; then
                  echo "Lockfile found. Running npm ci..."
                  npm ci --no-audit --no-fund
                else
                  echo "No package-lock.json found. Running npm install..."
                  npm install --no-audit --no-fund
                fi
                npm run build
              '
          """
        }
      }
    }

    stage('Prepare .env.deploy') {
      steps {
        writeFile file: "${DEPLOY_ENVFILE}", text: """
          # === Oracle compartido (un contenedor) ===
          ORACLE_HOST=${ORACLE_HOST}
          ORACLE_PORT=${ORACLE_PORT}
          ORACLE_SVC=${ORACLE_SVC}

          # === Backend Spring ===
          SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}
          DB_URL=${DB_URL}
          DB_USER=${DB_USER}
          DB_PASS=${DB_PASS}
          JWT_SECRET=${JWT_SECRET}
          SPRING_JPA_DIALECT=${ORACLE_DIALECT}

          # Puertos expuestos por ambiente
          BACKEND_PORT=${BACKEND_PORT}
          FRONTEND_PORT=${FRONTEND_PORT}

          # === Frontend ===
          PUBLIC_BACKEND_URL=${PUBLIC_BACKEND_URL}
          """
        sh "cat ${DEPLOY_ENVFILE}"
      }
    }

    stage('Deploy (docker compose up)') {
      steps {
        sh """
          docker compose --env-file ${DEPLOY_ENVFILE} -f ${DEPLOY_COMPOSE} up -d --build
        """
      }
    }
  }

  post {
    success {
      echo "Deployed ${env.BRANCH_NAME} OK"
    }
    failure {
      echo "Deployment failed for ${env.BRANCH_NAME}"
    }
  }
}