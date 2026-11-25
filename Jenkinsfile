pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t kotlin-sock-shop:1.0 .'
            }
        }
        stage('Deploy Container') {
            steps {
                sh 'docker rm -f my-sock-shop || true'
                sh 'docker run -d -p 8081:8081 --name my-sock-shop kotlin-sock-shop:1.0'
            }
        }
    }
}
