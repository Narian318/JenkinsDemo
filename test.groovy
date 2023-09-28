pipeline {
    agent any

    stages {
        stage('Get Source Code') {
            steps {
                git credentialsId: 'nn-github', url: 'https://github.com/Narian318/JenkinsDemo.git'
                echo 'Hello World'
            }
        }
        stage('Build code')
                {
                    steps
                            {
                                bat script: 'mvn compile'
                            }
                }
        stage('Run Test')
                {
                    steps
                            {
                                bat script: 'mvn test -Dbrowser=localchrome'
                            }
                }
        stage('Publish Report')
                {
                    steps
                            {
                                publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: '', reportFiles: 'target/surefire-reports/Extent*.html', reportName: 'Pipeline', reportTitles: ''])
                            }
                }
    }
}
