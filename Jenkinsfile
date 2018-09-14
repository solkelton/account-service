pipeline {
   agent any
   // { label ‘master’ }
   stages {
      stage(‘Build’) {
         steps {
            sh ‘gradle clean compileJava’
            sh ‘./gradlew clean build’
         }
      }


   }
}