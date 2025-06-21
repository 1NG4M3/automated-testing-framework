pipeline {
    agent any

    tools {
        jdk 'jdk17'
        gradle 'gradle-8.8'
        allure 'Allure'
    }

    parameters {
        choice(name: 'TEST_TYPE', choices: ['ALL', 'API', 'UI'], description: 'Какие тесты запускать')
    }

    environment {
        GRADLE_OPTS = "-Dorg.gradle.daemon=false"
        JAVA_HOME = "${tool 'jdk17'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/1NG4M3/automated-testing-framework', branch: 'master'
            }
        }

        stage('Check Java') {
            steps {
                sh 'java -version'
            }
        }

        stage('Fix gradlew') {
            steps {
                sh '''
                    echo "--- Convert gradlew to Unix format ---"
                    apt-get update && apt-get install -y dos2unix || true
                    dos2unix ./gradlew || true
                    chmod +x ./gradlew
                    ./gradlew --version
                '''
            }
        }

        stage('Run Selected Tests') {
            parallel {
                stage('Run API Tests') {
                    when {
                        anyOf {
                            expression { params.TEST_TYPE == 'API' }
                            expression { params.TEST_TYPE == 'ALL' }
                        }
                    }
                    steps {
                        sh '''
                            ./gradlew test --tests "*FilesControllerTest" \
                                           --tests "*GameControllerTest" \
                                           --tests "*JwtAuthenticationControllerTest" \
                                           --tests "*ResponseTrainControllerTest" \
                                           --tests "*StatusCodesControllerTest" \
                                           --tests "*UserControllerNewTest" || true
                        '''
                    }
                }

                stage('Run UI Tests') {
                    when {
                        anyOf {
                            expression { params.TEST_TYPE == 'UI' }
                            expression { params.TEST_TYPE == 'ALL' }
                        }
                    }
                    steps {
                        sh '''
                            ./gradlew test --tests "*AlertsFrameWindowsTest" \
                                           --tests "*ElementsTest" \
                                           --tests "*FormsTest" \
                                           --tests "*GameStoreApplicationTest" \
                                           --tests "*InteractionsTest" \
                                           --tests "*WidgetsTest" || true
                        '''
                    }
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                echo "Публикация Allure отчета..."
                sh 'ls -la build/allure-results || echo "Результаты Allure не найдены!"'
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']], reportBuildPolicy: 'ALWAYS'
            }
        }
    }

    post {
        always {
            junit 'build/test-results/test/*.xml'
        }
    }
}