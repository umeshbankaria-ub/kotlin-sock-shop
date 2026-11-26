pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build Application') {
            steps {
                sh 'chmod +x gradlew && ./gradlew compileKotlin compileTestKotlin --no-daemon'
            }
        }
        stage('Unit Tests (UT)') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
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
