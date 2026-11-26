pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Run Tests') {
            steps {
                sh 'chmod +x gradlew && ./gradlew test --no-daemon'
            }
        }
        stage('Build Docker Image') {
            when { branch 'master' }
            steps {
                sh 'docker build -t kotlin-sock-shop:1.0 .'
            }
        }
        stage('Import Image to k3s') {
            when { branch 'master' }
            steps {
                sh 'docker save kotlin-sock-shop:1.0 | sudo /usr/local/bin/k3s ctr images import -'
            }
        }
        stage('Deploy to Kubernetes') {
            when { branch 'master' }
            steps {
                sh 'sudo /usr/local/bin/k3s kubectl rollout restart deployment/kotlin-sock-shop'
                sh 'sudo /usr/local/bin/k3s kubectl rollout status deployment/kotlin-sock-shop'
            }
        }
    }
}
