Building the Project

Pre requisites
The project is built using maven. Please ensure you have a working version of maven installed in your system before trying to build the module. 
-	Maven
-	IntelliJ IDEA
Below is the list of repositories which are used to download dependencies
-	Central : http://repo1.maven.org/maven2/
-	java.net.m2: http://download.java.net/maven/2/
-	Sonatype: http://repository.sonatype.org/content/groups/public/
SDK for the project is jdk 1.8.0_201
Build
-	Unzip the package zopa-technical-challenge-sources.zip to a local folder
-	Open the root project using Intellij IDEA
-	If maven plugin is available, run install goal in the plugin
-	 
-	If Not available, open a command prompt, navigate to the root pom location and run the below command
o	Mvn -U clean install
The latest code is available in github repository : https://github.com/harish-janardhanan/technical-challenges 
The repository is private, please drop a line to harish007007@gmail.com for access.

Running the packaged application 

The application is assembled into an uber-jar with all dependencies. The package contains the below artefacts
-	Uber jar with dependencies: RateCalculator-jar-with-dependencies.jar
-	Standalone jar without dependencies: RateCalculatorClient-0.0.1-SNAPSHOT.jar
-	Logging configuration : log4j.xml
 To run the already built application, please follow the below steps
-	Unzip the package RateCalculator-0.0.1-SNAPSHOT.zip
-	Open command prompt and navigate to the directory <dir>/RateCalculator
-	Run the below command
o	java -Dlog4j.configuration=file:log4j.xml -jar RateCalculator-jar-with-dependencies.jar [marketdatafile] [amount]
o	marketdatafile – csv file with rates
o	amount – input loan amount request
-	log4J is by default configured at INFO level. Please manually change to different levels if required
-	without the correctly pointing to the log4j, program will issue log4j initialization warnings
-	Application logs will be generated in <currentdir>/RateCalcuator/logs/
Example runs
Available quote
 
 
Not Available quote
 

Incorrect Parameter
 

