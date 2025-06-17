pipeline {
    agent any

    tools {
        jdk 'jdk17'
        allure 'Allure'
    }

    environment {
        GRADLE_OPTS = "-Dorg.gradle.daemon=false"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/1NG4M3/automated-testing-framework', branch: 'master'
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Check gradlew') {
            steps {
                sh '''
                    echo "--- ls -la ---"
                    ls -la
                    echo "--- which gradlew ---"
                    which ./gradlew || echo "gradlew not found in PATH"
                    echo "--- file gradlew ---"
                    file ./gradlew || echo "file error"
                '''
            }
        }

        stage('Debug files') {
            steps {
                sh 'pwd && ls -la'
            }
        }

        stage('Parallel Tests') {
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

        stage('Generate Allure Report') {
            steps {
                sh './gradlew allureReport'
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