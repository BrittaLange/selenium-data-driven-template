# template-parallel-execution
Selenium, TestNG, ExtentReport Automation Testing Framework
This framework is build in eclipse, it is a maven project. It uses Selenium, TestNG and ExtentReport so that we can create testcases that run automatically, even in parallel execution. And we get a clearly arranged report of all tests that were executed.
For improved test case maintenance and reduced code duplication the page object model design pattern is implemented.

## Data-driven testing
The test case ShopLoginTest is implemented as a data-driven test. It gets its data from an excel file. Apache POI is used to read data from the excel file.

## Cloning the project
After cloning the project make sure to change the name of the project in the pom.xml file and in the ExtentManager class in the method "getReport()".
