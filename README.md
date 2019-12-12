# VerivoxTestAutomationTask

This task is for automating DSL Calculator feature in Verivox website  

**Technologies Used:**   
- Cucumber  
- Java  
- TestNG  

**Prerequisites to run the automation test:**    
1- Java installed on the machine  
2- Maven installed on the machine  
  
**Steps to run the automation test:**  
1- Open cmd   
2- Go to project location  
3- Run 'mvn test' command  
4- To check the run result open 'VerivoxAutomationTestReport.html' from '{Project Location}\test-output' folder  

**Steps to change project config:**  
1- Open 'config.properties' file from '{Project Location}\src\main\resources' folder  
2- There are two configurations:  
	- runHeadless: if set to 'true' the browser will run in headless mode  
	- waitTimeout: set the wait timeout for the elements in seconds  

