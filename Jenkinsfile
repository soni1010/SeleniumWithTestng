pipeline {
    agent any 
    stages {
        stage('Clone Repository') {
            steps {
                git url: 'https://github.com/soni1010/SeleniumWithTestng'
            }
        }
       
    
        stage('Publish Test Results') {
            steps {
                junit '**/Output.txt'
            }
        }
    }
}
