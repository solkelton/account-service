pipeline {
   agent any
   stages {
      stage(‘Build’) {
         steps {
            sh ‘gradle clean compileJava’
            sh ‘./gradlew clean build’
         }
      }
   }
}