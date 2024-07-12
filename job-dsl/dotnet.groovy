job('DotNetExample') {
    scm {
        git('https://github.com/bigboybamo/LinuxTest.git') {  node -> // replace with your .NET project's Git repository
            node / gitConfigName('bigboybamo')
            node / gitConfigEmail('oyetubobamiji@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
    }
    steps {
        shell("""
            # Install .NET SDK if needed
            if ! command -v dotnet &> /dev/null
            then
                echo ".NET SDK not found, installing..."
                # Example installation for Ubuntu
                wget https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
                sudo dpkg -i packages-microsoft-prod.deb
                sudo apt-get update
                sudo apt-get install -y apt-transport-https
                sudo apt-get update
                sudo apt-get install -y dotnet-sdk-6.0
            fi

            # Restore .NET dependencies
            dotnet restore

            # Build the project
            dotnet build

            # Run tests
            dotnet test
        """)
    }
}
