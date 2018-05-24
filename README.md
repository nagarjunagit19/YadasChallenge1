** Project Name - BRANCH

** How to clone the repository
https://github.com/nagarjunagit19/YadasChallenge1.git

** Prerequisites to run the tests
 - Java 1.8
 - Maven 3.3.9

** How to run the test(s)

 To run all the test use the below command from the project folder path
 - mvn clean install -DbrowserName=chrome (If running for the first time, to install all the dependencies and run the tests)
 - mvn tests -DbrowserName=chrome (if dependencies are already available)
 NOTE: If no browserName is specified, default value will be chrome
 
** To run single module (Eg: All LoginTests) use the below command from the project folder path
 - mvn test -Dtest=io.branch.tests.TeamTests.java
you can also run from testng.xml 
 
 # To run a single/few tests
 - mvn test -Dtest=io.branch.tests.TeamTests.java#validateSignUpForDemoRequest

-Using TestNG
You can use exclude tag in testng.xml Example,  <exclude name="testMethodName" />
OR add "enabled=false" in source of test class to ignore the test
 
** How to look for the logs
 - Log file is available in the project folder path with the name "branch.log". Before every run, log file clears

** TO-DO List:
   Test reports can be automated
   Integrate code into jenkins CICD

** Functionality assumptions
	As per the instructions, launching www.google.com is the starting point of execution. So it is mentioned in before test method.
 	Each test  should start in new browser and should never execute in an existing browser
  Employee name is used as the unique identifier for each employee. If there are duplicate name, we can use some other attribute to identify them


** Modules Covered:
 - Team Page

** Test Report 
 - Test report of all scripts are found in test-output -> index.html
 - Also placed a separate document in the project folder (TestReport.xlsx)

