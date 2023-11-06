pipeline {
    agent any
environment {
    		DOCKERHUB_CREDENTIALS=credentials('projet')
    	}

    stages {
        stage('Récupération du code de la branche') {
            steps {
                git branch: 'MahdiENNOUR-5NIDS-G3',
                url: 'https://github.com/BahaEddineKsib/5NIDS2-G3-PROJECT2.git'
            }
        }

        stage('Nettoyage et compilation avec Maven') {
            steps {
                // Étape de nettoyage du projet
                sh "mvn clean"

                // Étape de compilation du projet
                sh "mvn compile"
                // Etape de sonar
            }
        }

        stage('MVN SONARQUBE'){
        steps {
            sh "mvn sonar:sonar -Dsonar.login=sqa_302c9ed696110e75ec002a6bcecfb4f796e1e7de"
        }
        }
        stage("mockito"){
            steps {
                sh 'mvn -Dtest=mockito test'
            }
        }
        stage('mvn deploy'){
            steps {
                sh "mvn deploy"
            }
        }

        stage('Docker Image') {
                           steps {
                               sh 'docker build -t mahdiennour-5nids2-g3 .'
                           }
               }
stage('DOCKERHUB') {
                          steps {
                              sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                              sh 'docker tag mahdiennour-5nids2-g3 mahdienn/mahdienn-5nids2-g3:1.0.0'
                              sh 'docker push mahdienn/mahdienn-5nids2-g3:1.0.0'
                          }
                      }
               stage('Docker Compose') {
                                  steps {
                                      sh 'docker compose up -d'
                                  }
                      }
stage('Junit') {
            steps {
                sh 'mvn -Dtest=Junit test'
            }
        }
stage('Grafana/prometheus') {
            steps {
                sh 'docker start e7720cf8a384'
                sh 'docker start d51ba09c7cf6'
            }
        }

    }
}
