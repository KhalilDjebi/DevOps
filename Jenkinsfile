pipeline {
agent any

stages {
         stage('GitHub Clone') {
                steps {
                    git branch: 'khalildjebi', url: 'https://github.com/KhalilDjebi/DevOps.git'
                }  
            }
			
			stage('Docker compose') {
            steps {
            sh 'docker-compose up -d';
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
          -Dsonar.host.url=http://192.168.43.40:9000 \
          -Dsonar.login=6a70cbb7f9c08fc9487487c4fb5de42d074174ce"
    }
    }
	 stage('Nexus Deploy') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
	
	
	stage("Docker Image") {
                       steps{

                           sh 'docker build -t khalildjebi/achat .'
                       }
               }
           stage("DockerHub Login") {
                       steps{
                           sh 'echo "Login to dockerhub in progress" | docker login -u khalildjebi -p azerty12345'
                       }
               }
           stage("DockerHub Push") {
                       steps{
                        sh 'docker push khalildjebi/achat'
                   }
              }
			  
			  
}
   post { 
    success { mail to: "khalil.djebbi@esprit.tn", subject: "Succes Notification", body: " Job done ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL}" }
		
    failure { mail to: "khalil.djebbi@esprit.tn", subject: "Pipeline Failure", body: " Failure on job ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL} " } 
}
}