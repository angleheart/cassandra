# cassandra
Stock Market Analysis Tool

Requirements:<br />
  -SQL Database<br />
  -MarketStack API Key<br />

To Use:<br />
  -Run the provided SQL script on an SQL database<br />
  -Obtain marketstack API key<br />
  -Set the config to your API and database credentials<br />
  -Pass config as argument to main (Example: java -jar Cassandra.jar ./cassandra.properties)<br />
  -Use the command line to interact<br />


Command Line Instructions:<br />
  -The functionality is similar to that of a traditional filesystem<br />
  -As you run commands, the prompt will specify the current location in the program<br />
  -Type "help" to access local commands based on current console location<br />
  -Type "HELP" to access super commands which can be accessed from any console program<br />
  -The command "end" is used to "go back"<br />
  -Super commands are defined in the abstract console class<br />
  -Local commands are defined in any created console class that extends Console<br />

This program is currently very basic, but there are endless possibilities.<br />
To add features you can simple extend Console and create a new one or add functionality to an existing.<br />
