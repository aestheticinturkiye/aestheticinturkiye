pipeline {
    agent any

    environment {
        // GitHub PAT ve kullanıcı adı ile giriş yapacağınız bilgileri tanımlayın
        GITHUB_USERNAME = credentials('aestheticinturkiye')
        GITHUB_PAT = credentials('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')
    }

    stages {
        stage('Checkout Source') {
            steps {
                // Kaynak kodu klonla
                git branch: 'main', url: 'https://github.com/aestheticinturkiye/aestheticinturkiye',
                    credentialsId: 'github-username-pat'
            }
        }

        stage('Setup Java') {
            steps {
                // Java kurulumu (Temurin 21 kullanımı)
                sh 'sudo apt-get update'
                sh 'sudo apt-get install openjdk-21-jdk -y'
            }
        }

        stage('Build Backend Project') {
            steps {
                // Maven kullanarak projeyi build et
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Login to Docker Hub') {
            steps {
                // Docker Hub'a giriş yap
                sh 'echo Mertatag123. | docker login -u mertatag123 --password-stdin'
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                // Docker imajını build et
                sh 'docker build -t mertatag123/backendv1 .'
            }
        }

        stage('Push Backend Docker Image') {
            steps {
                // Docker imajını Docker Hub'a gönder
                sh 'docker push mertatag123/backendv1:latest'
            }
        }

        stage('Deploy to Server') {
            agent { label 'self-hosted' }
            steps {
                script {
                    // Backend imajını çek
                    sh 'docker pull mertatag123/backendv1:latest'

                    // Eski Backend container'ı durdur ve kaldır
                    sh 'docker rm -f backend || true'

                    // Eski Backend imajını kaldır
                    sh '''
                        old_image=$(docker images mertatag123/backendv1 --format "{{.ID}}" | tail -n +2)
                        if [ ! -z "$old_image" ]; then
                            docker rmi $old_image
                        fi
                    '''

                    // Yeni Backend container'ını başlat
                    sh '''
                        docker run -d \
                        --restart unless-stopped \
                        --network my-network \
                        -p 8080:8080 \
                        --name backend \
                        --link postgres1:postgres \
                        -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/esthomy_db \
                        -e SPRING_DATASOURCE_USERNAME=esthomyuser \
                        -e SPRING_DATASOURCE_PASSWORD=esthomy2000* \
                        mertatag123/backendv1:latest
                    '''
                }
            }
        }
    }
}
