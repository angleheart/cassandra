# cassandra
Stock Market Analysis Tool

Requirements:
-SQL Database
-MarketStack API Key


To Use:
-Run the provided SQL script on an SQL database
-Obtain marketstack API key
-Set the config to your API and database credentials
-Pass config as argument to main (Example: java -jar Cassandra.jar ./cassandra.properties)
-Use the command line to interact


Command Line Instructions:
-The functionality is similar to that of a traditional filesystem
-As you run commands, the prompt will specify the current location in the program
-Type "help" to access local commands based on current console location
-Type "HELP" to access super commands which can be accessed from any console program
-The command "end" is used to "go back"
-Super commands are defined in the abstract console class
-Local commands are defined in any created console class that extends Console

This program is currently very basic, but there are endless possibilities.
To add features you can simple extend Console and create a new one or add functionality to an existing.
