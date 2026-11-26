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
        stage('Import Image to k3s') {
            steps {
                sh 'docker save kotlin-sock-shop:1.0 | sudo /usr/local/bin/k3s ctr images import -'
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh 'sudo /usr/local/bin/k3s kubectl rollout restart deployment/kotlin-sock-shop'
                sh 'sudo /usr/local/bin/k3s kubectl rollout status deployment/kotlin-sock-shop'
            }
        }
    }
}
