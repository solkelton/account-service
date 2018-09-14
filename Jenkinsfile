pipeline {
    agent any
    // { label 'master' }
    stages {
    stage ('Clone') {
                steps{
            git url: 'https://github.com/solkelton/account-service.git'
            }
       stage('Build') {
          steps {
             sh './gradlew clean build'
          }
       }
    }
}