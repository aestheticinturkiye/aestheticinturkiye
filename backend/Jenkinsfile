pipeline {
    agent any

    stages {
        stage('Compile and Clean') {
            steps {
                sh 'mvn clean compile' // Spring Boot uygulamanızı derle
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t mustafacolakoglu28/esthomy:${BUILD_NUMBER} .'
            }
        }

        stage('Docker login') {
            steps {
                withCredentials([string(credentialsId: 'DockerId', variable: 'DockerPwd')]) {
                    sh 'docker login -u mustafacolakoglu28 -p ${DockerPwd}'
                }
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push mustafacolakoglu28/esthomy:${BUILD_NUMBER}'
            }
        }
        stage(Archiving) {
            steps {
                archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
