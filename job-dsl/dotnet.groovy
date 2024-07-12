pipeline {
    agent any

    tools {
        dotnet '.NET Instal' // Use the name of your .NET SDK configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/bigboybamo/LinuxTest.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh '''
                    # Restore .NET dependencies
                    dotnet restore

                    # Build the project
                    dotnet build

                    # Run tests
                    dotnet test
                '''
            }
        }
    }

    triggers {
        scm('H/5 * * * *')
    }
}
