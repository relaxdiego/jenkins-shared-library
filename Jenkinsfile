pipeline {
    agent any

    tools {
        maven 'Maven 3.5.2'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean install'.
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'.

                    jacoco classPattern: '**/target/classes',
                           execPattern: '**/target/coverage-reports/jacoco-ut.exec',
                           sourcePattern: '**/src/org/yourcompany',
                           exclusionPattern: '**/target/classes/*closure*.class'
                }
            }
        }
    }
}
