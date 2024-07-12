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
		
            # Restore .NET dependencies
            dotnet restore

            # Build the project
            dotnet build

            # Run tests
            dotnet test
        """)
    }
}