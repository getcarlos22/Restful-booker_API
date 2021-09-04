# Restful-booker API
Automated API tests for Hotel Booking Endpoints

## Dependencies
Here are the dependencies used in the project for development & testing perspective.

* [Java 1.8](https://www.java.com/en/) - Coding Language
* [Maven](https://maven.apache.org/) - Dependency Management
* [RestAssured](http://rest-assured.io/) - Used to get API Response
* [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - GSON is a Java library that converts Java Objects into JSON and vice versa
* [TestNG](https://testng.org/) - Testing framework inspired from JUnit and NUnit


##Functionalities
* createBooking and getBookingById endpoints Test
* partialUpdateBooking, deleteBooking and getBookingIds test get all bookingIds without filter
* Access report target/surefire-reports or test-output


## Getting Started
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
