
1. Clone the project from Github

git clone git@github.com:muhammadrezaulkarim/FileUtility.git

2. Build the project in Windows command prompt or Linux terminal with Apache Maven from the application root directory:

mvn -U clean package

All unit tests in the 'src\test\java\solutions' directory will be executed during the build process.

3. Copy all necessary files (app-config.json and log4j2.xml) from the 'src\main\resources' directory to the 'target' directory.

4. Edit the app-config.json file and specify the test file location (absolute path of the directory containing the test file) and test 

file name. Data directory has several test files. 'log.json' is the default test file name.

5. Move to the 'target' directory in Windows command prompt or Linux terminal and execute the application:

java -jar solutions-0.0.1-SNAPSHOT-jar-with-dependencies.jar


*** Please note that Java 8 and Apache Maven is a pre-requisite for this project. 


Set Up Maven in Windows 10:

Download Apache Maven from the following link:
https://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip

Unzip and place the the files a directory like this:
C:\Users\UserX\apache-maven-3.6.3

'UserX' needs to be replaced with actual Windows user name.

Set up a Windows environment variable for Maven:
variable name: MAVEN_HOME

variable value: C:\Users\UserX\apache-maven-3.6.3

Add the following entry in Windows system variable path:

%MAVEN_HOME%\bin
