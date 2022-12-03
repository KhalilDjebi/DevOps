pipeline {
agent any

stages {
         stage('GitHub Clone') {
                steps {
                    git branch: 'khalildjebi', url: 'https://github.com/KhalilDjebi/DevOps.git'
                }  
            }



               stage('Maven Clean') {
                        steps {
                           sh 'mvn clean '
                        }
                    }

          stage('Maven Compile') {
            steps {
               sh 'mvn compile'
           }
        }

          stage('Maven Test') {
            steps {
               sh 'mvn test'
            }
        }
          stage('Maven Verify') {
             steps {
               sh 'mvn verify'
          }
       }
       stage ('Scan Sonar'){
    steps {
    sh "mvn sonar:sonar \
          -Dsonar.projectKey=achat \
          -Dsonar.host.url=http://http://192.168.43.149:9000 \
          -Dsonar.login=fcf7cf274ff3a9f9328adae768e331b29628f56b"
    }
    }
   
    stage('Nexus Deploy') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
   
}
}
    
 
