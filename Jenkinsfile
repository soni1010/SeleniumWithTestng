pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/soni1010/SeleniumWithTestng'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'mvn test'  // Replace with 'sh' if on Linux/Mac
            }
        }
        stage('Archive Output') {
            steps {
                archiveArtifacts artifacts: 'Output.txt', fingerprint: true
            }
        }
        stage('Display Output') {
            steps {
                script {
                    def output = readFile 'Output.txt'
                    echo output
                }
            }
        }
    }
}
