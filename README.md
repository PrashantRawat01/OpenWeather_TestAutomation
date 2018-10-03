
Descript: This is a demo project for OpenWeather Application "https://openweathermap.org/"
Just creating with the object to have good covering of end to end framework with the given timeframe.

Features it support:
1) It’s a Selenium webdriver based test automation project.
2) TestNG framework is used to drive the automation testing.
3) Maven is integrated for Build & Compilation.
4) Log4j is implemented for better reporting and proper logging structure.
5) Support Parallel test execution of test cases at Class level with multiple threads.
6) Support provided for multiple web browser like: Chrome & Firefox.
7) The locators are externalized so there is flexibility to change without much rework.
8) The test cases are data driven and these input data’s can be passed from testng xml.
9) Property file is used for configuration so there is no hardcoding of paths, this makes it very flexible in terms of a code folder change or platform changes, in no time it can be managed.
10) Supports command line execution so no need to open the code to run the test automation scripting.
11) The folder structure and libs are not needed to be predefine and the project can be downloaded/ placed from github at any location, Since it has dynamic paths, this will work without any huddle.
12) It supports Jenkins pipeline for Continuous Integrations and Deployment. 

==========================================
Setup of Selenium web-driver, TestNG, XSLT report & Basic things

Must: Things to have it in the native Machine:

1.	Download & Install Java on your Computer
Version: Java 1.8
Install:  As usual by following the instructon.

2.	Install Eclipse on your computer 
Download link: http://www.eclipse.org/downloads/
Version: Eclipse Java EE IDE for Web Developers.
Install: As usual

3.	Download the Maven from https://maven.apache.org/
Setup the maven for the Test machine

4.  Download links: All of them can be find on google with various publisher. 
Version: Just make sure the latest available versions so that it supports most of the features with latest bugs fixes.
Install: we can have zip files (without unzipping, because it can be linked to class path without any issue).


5.	Also it’s important to set some of the class path & in system environment variable in which important ones are:

•	For Jdk 1.8, need to add the path variable in “environment variables” otherwise “ant build fire” throws an error saying “tools.jar” is not available in the system.
o	Java 1.8 directory location: C:\Program Files\Java\jdk1.8.0_51
o	Goto My Computer-> Properties-> Advance Computer Settings->Environment Variables-> System Variable 
o	Create a new “variable name” as “JAVA_HOME”
o	Paste the above java directory in the “variable value” textbox and Click “Ok”
o	Search for a “PATH” variable in the same. 
o	Now click on Edit button to modify its data.
o	Append a semicolon”;” and paste the above java directory appending “\bin”



6.	Download & Setting up MAVEN

•	Description: Download maven as provided step before.
•	Configuring Maven:
	Maven directory location: Any where on the machine (C:\MAVEN\)
o	Goto My Computer-> Properties-> Advance Computer Settings->Environment Variables-> System Variable 
o	Create a new “variable name” as “MAVEN_HOME”
o	Paste the above MAVEN directory in the “variable value” textbox and Click “Ok”
o	Search for a “PATH” variable in the same. 
o	Now click on Edit button to modify its data.
o	Append a semicolon”;” and paste the above java directory appending “\bin”

To check it its configurability: Launch cmd prompt, type mvn -version


7.	Download & Configuring TestNG: 

•	Download Link: http://beust.com/eclipse
•	Configuring TestNG:
o	After installing Eclipse, Open it
o	Goto Help->Install New Software
o	Give name as: “TestNG” in the “Work with” textbox
o	Click on Add button                
o	Give name as “TestNG” in the “Name” field & Download Link in the “Location” field
o	Click “Ok”
o	After a while you will see under the Name column “TestNG”  Bundle will Appear
o	Click on the Checkbox, appear next to it.
o	Click on “Next”  button 
o	Accept the license
o	And Click on “Finish” button to complete the operation
o	If any popup displayed for license then also install by clicking “Ok”

•	Description: The above process will install the TestNG Plug-in into eclipse. To check whether  TestNG  is installed properly into the Eclipse:
o	Goto  Window->Show View->Others
o	In the Show View Dialog, Goto Java, to open the hierarchy and you will find TestNg into it.
o	Click on TestNG, then OK button.
o	If any error message is not displayed it means, TestNG configured ccorrectly.



8.	Download & Configure project
Download it from git repo, as provided in email.

9.	Setup TestProject in Eclipse for Automation using Selenium:

•	First step is to create a Java Project:
o	Go to File menu > New > Maven Project.
o	Enter Project Name as “Pru_openweather_Backup”
o	Click on Finish button.
o	Under newly created project “Pru_openweather_Backup” you will see the “src“ folder & right click on the “src” folder which is holding the code.

10	Second step is to setup project to run through Maven.
o	Now Right click on “Pru_openweather_Backup” & select RunAs->Maven Clean option.
o	Now Right click on “Pru_openweather_Backup” & select RunAs->Maven Install option.
o	Now Right click on “Pru_openweather_Backup” & select RunAs->Maven Run option.
o	This should have all the necesarry dependent project downloaded and running test cases.

o	For a sucessful test execution, there will be a TestNG execution report
	This will be available at: ..//target\surefire-reports\index.html

11	The same cane be done using Jenkis pipeline with below script:
	pipeline {
	agent any
	
	stages {
		stage ('Compile Stage') {
			steps{
				withMaven (maven: 'maven_3_5_4'){
				sh 'mvn clean compile'
				}
			}
		}
		stage ('Testing Stage') {
			steps{
				withMaven (maven: 'maven_3_5_4'){
				sh 'mvn test'
				}
			}
		}
		stage ('Deployment Stage') {
			steps{
				withMaven (maven: 'maven_3_5_4'){
				sh 'mvn deploy'
				}
			}
		}		
		}
	}

12	Now we are good to start with class creation in Eclipse.
o	Create some testScript classes under _testScript folder so we can have the test suite which we can run, form statistics and create report using above setup.
o	For example:


13.	Create a “testng.xml”:

•	Description: By help of it we can execute the created “TestSuit”.
o	Select the project and right Click on to it and 
o	Hover upto New->Others->TestNG->TestNG class, and click it
o	Browse the “src” folder of current project into “Source folder” field
o	Browse the “_testExecutor” folder into “Package name” field
o	Provide a “classname” as “TestNgClass” into “Class name” field.
o	Provide an “xml” name as “testing.xml” into “XML Suite file” field.
o	Now open this “testng.xml” and create test cases execution sequence list which need to be execute.
o	Format need to be as below (For example) {Here I have listed TestNgClass (which is a class which will create automatically during above process of creating “testng.xml”, but name can be modified), Webdriver_class, Webdriver_class2, & Webdriver_class3 (which are my Test cases under package _testExecutor) }:


<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="Regression suite 1" verbose="1"  parallel ="classes" thread-count="1">
<parameter name="BrowserType"  value="Chrome"/>
  <test name="Test1">
    <classes>
      <class name="main.testScripts.Test1"/>
    </classes>
  </test>
    <test name="Test2" >
    <classes>
      <class name="main.testScripts.Test2"/>
       <parameter name="Param1" value="abcde"/> 
    </classes>
  </test>
    <test name="Test3" >
    <classes>
      <class name="main.testScripts.Test3"/>
       <parameter name="Param1" value="Mumbai"/> 
    </classes>
  </test>
    <test name="Test4" >
    <classes>
      <class name="main.testScripts.Test4"/>
    </classes>
 </test>
</suite>


14.	Execute of the TestSuit & generating TestNg Reports:

•	Test execution in Selenium is possible without the “TestNG” framework then it will generate the report using default framework.
•	Using “TestNG” framework:
o	Select the project and right Click on to it 
o	Hover upto Run As->TestNG Test, and click it
o	This will run/Execute all listed test cases in sequence as mentioned in the “TestNgClass”
o	Here we can see the normal execution report under “Console” window which is in Text format.
o	The “TestNg” report can be seen under “Result of running class TestNgClass” which is a little detailed result report with proper pass & fail items.
o	To See the TestNg detailed report
o	Open the new created “test-output” folder (this can be seen after refreshing the project or as on physical location)
o	Open the “index.html” to see the TestNG html fully detailed report.
o	The TestNg report is nothing but which gets generated at time of “ant testRun” command.
o	

POM XML: 

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>Pru_openweather_G01</groupId>
  <artifactId>Pru_openweather_A01</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <resources>
      <resource>
        <directory>src</directory>
        <excludes>
          <exclude>**/*.java</exclude>
        </excludes>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.5.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      
      <!-- Following plugin executes the testng tests -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.18.1</version>
				<configuration>
				<!-- Suite testng xml file to consider for test execution -->
					<suiteXmlFiles>
						<suiteXmlFile>testng.xml</suiteXmlFile>
					</suiteXmlFiles>
				</configuration>
			</plugin>
			<plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-report-plugin</artifactId>
          <version>2.22.0</version>
        </plugin>
    </plugins>
  </build>
  <!-- Add Following Lines in Your POM File -->
	<properties>
		<selenium.version>3.14.0</selenium.version>
		<testng.version>6.9.10</testng.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>${selenium.version}</version>
		</dependency>
		<dependency>
			<groupId>org.testng</groupId>
			<artifactId>testng</artifactId>
			<version>${testng.version}</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-api</artifactId>
			<version>2.8.2</version>
		</dependency>
		<dependency>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-core</artifactId>
			<version>2.8.2</version>
		</dependency>
	</dependencies>
</project>

 
====================================================================

15. Running test script through command prompt:
o	Open Command prompt
	Go to project repository, where POM.xml file located for this project.
	Run command -> mvn clean
	Run command -> mvn install
	Run command -> mvn test
	
	This should also run the test cases in the same fashion as its executed through eclipse.


16.	 Scripts Creations:

•	As of now this is just a very basic framework, which depicts only folder structure, defaults Reporting types & Some test script, or & common libraries:
•	As this is a TestNg & Maven driven framework, so create any test cases (class) by: RightClick on _testScript ->others->TestNg->TestNgClass-> Name, Next button, finish


17.	 Expected framework modification:

•	There are many modification/Implementation can be expected or created/in-scoped in future:
o	XLS reporting
o	Database Handling
o	Need to work in direction of creating a batch file to run these test cases on one click.
