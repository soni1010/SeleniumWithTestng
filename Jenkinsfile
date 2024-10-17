pipeline {
    agent any 
    stages {
        stage('Clone Repository') {
            steps {
                git url: 'https://github.com/soni1010/SeleniumWithTestng'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
