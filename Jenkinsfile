pipeline {
    agent any

    environment {
        VM_IP = '127.0.0.1'
        VM_PORT = '2202'
        SSH_USER = 'vagrant'
        SSH_KEY = credentials('vagrant-ssh-key')
    }
    stages {
        stage('Checkout') {
             steps {
                git branch: 'main', url: 'https://github.com/GustavoMedeiros-A/sistema-enade'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('enade-app', '-f Dockerfile .')
                }
            }
        }

        stage('Save Docker Image') {
            steps {
                script {
                    sh 'docker save enade-app:latest -o enade-app.tar'
                }
            }
        }

        stage('Copy Docker Image to VM') {
            steps {
                sshagent([SSH_KEY]) {
                    sh """
                        scp -P ${VM_PORT} -i ${SSH_KEY} enade-app.tar ${SSH_USER}@${VM_IP}:/home/${SSH_USER}/enade-app.tar
                    """
                }
            }
        }

        stage('Load Docker Image on VM') {
            steps {
                sshagent([SSH_KEY]) {
                    sh """
                        ssh -p ${VM_PORT} -i ${SSH_KEY} ${SSH_USER}@${VM_IP} 'docker load -i /home/${SSH_USER}/enade-app.tar'
                    """
                }
            }
        }

        stage('Deploy Application') {
            steps {
                sshagent([SSH_KEY]) {
                    sh """
                        ssh -p ${VM_PORT} -i ${SSH_KEY} ${SSH_USER}@${VM_IP} 'docker run -d --name enade-app -p 8080:8080 enade-app:latest'
                    """
                }
            }
        }
    }
}
