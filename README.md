# Restful-booker API
[![CircleCI](https://circleci.com/gh/getcarlos22/Restful-booker_API.svg?style=svg)](https://circleci.com/gh/getcarlos22/Restful-booker_API)

Automated API tests for Hotel Booking Endpoints

## Dependencies
Here are the dependencies used in the project for development & testing perspective.

* [Java 1.8](https://www.java.com/en/) - Coding Language
* [Maven](https://maven.apache.org/) - Dependency Management
* [RestAssured](http://rest-assured.io/) - Used to get API Response
* [Allure](https://mvnrepository.com/artifact/io.qameta.allure/allure-junit5) - Allure Framework is a flexible lightweight multi-language test report tool
* [Junit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api) - Testing framework JUnit


##Functionalities
* createBooking and getBookingById endpoints Test
* partialUpdateBooking, deleteBooking and getBookingIds
* Allure Report  - navigate to target/allure-results folder. Run the command `allure serve ./`

## Getting Started
* Import project to IDE (Intellij recommended)
* Set-Up Junit Run config\debug or
* Run 'All Tests' src\test\java - Junit runner

## Alternatively
```
1) Open your terminal and do a clone of this project.
2) Navigate to the respective directory and run below command.
   mvn clean install
3) Above command will build the project along with test cases. 
````
````
[INFO] ------------------------------------------------------------------------
[INFO]  T E S T S
[INFO] ------------------------------------------------------------------------

[INFO] Results:
[INFO]

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
