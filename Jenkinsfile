pipeline {
agent any

stages {
 

         stage('Cloning from GitHub') {
                steps {
                    git branch: 'main', url: 'https://github.com/KhalilDjebi/DevOps.git'
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
          -Dsonar.host.url=http://192.168.43.149:9000 \
          -Dsonar.login=2bb7c66a6d3230af7afee2d9858bad13823ea60c"
    }
    }
    
    	 stage('Nexus Deploy') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    
    
           
			  
}


}
