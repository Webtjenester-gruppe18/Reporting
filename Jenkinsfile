pipeline {
   agent any
   stages {
     stage('Test') {
       steps {
         sh 'mvn clean install'
       }
     }
      stage('Build') {
           steps {
             sh 'docker build --tag reportingimage:latest . '
           }
         }
      stage('Deploy') {
           steps {
             sh '''docker stop reporting || true && docker rm reporting || true;
             docker run -d -p 7272:8080 --name reporting reportingimage:latest
             '''
           }
      }
   }
 }