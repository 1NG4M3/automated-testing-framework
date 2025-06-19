pipeline {
    agent any

    tools {
        jdk 'jdk17'
        gradle 'gradle-8.8'
        allure 'Allure'
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

        stage('Run Tests in Parallel') {
            parallel {
                stage('Run API tests') {
                    steps {
                        sh '''
                            ./gradlew test --tests "*FilesControllerTest" \
                                           --tests "*GameControllerTest" \
                                           --tests "*JwtAuthenticationControllerTest" \
                                           --tests "*ResponseTrainControllerTest" \
                                           --tests "*StatusCodesControllerTest" \
                                           --tests "*UserControllerNewTest"
                        '''
                    }
                }
                stage('Run UI tests') {
                    steps {
                        sh '''
                            ./gradlew test --tests "*AlertsFrameWindowsTest" \
                                           --tests "*ElementsTest" \
                                           --tests "*FormsTest" \
                                           --tests "*GameStoreApplicationTest" \
                                           --tests "*InteractionsTest" \
                                           --tests "*WidgetsTest"
                        '''
                    }
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            }
        }
    }

    post {
        always {
            junit 'build/test-results/test/*.xml'
        }
    }
}