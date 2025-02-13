# Backend_Traini8_SuryanshSrivastava

This project provides a REST API for managing training centers. 
It allows users to create and retrieve training center information. 
The data is stored in MySQL DB.

## Features

- Insert, Create a new training center with fields like `CenterName`, `CenterCode`,`Address`, `StudentCapacity`, `CoursesOffered`, `ContactEmail`, and `ContactPhone`.
- fetch all.
- fetch By Id.
- Delete all training centers stored in the database.
- Delete By Id.
- Annotation based validation for input fields like `CenterName`, `Email`, and `Phone Number`.

## Prerequisites

Before setting up the application, make sure you have the following:

1. **Java 17+**: This application is built with Java 17. You can download it from [here](https://adoptium.net/).
2. **Maven**: Maven is used to build the project. If you don't have it installed, you can download it from [here](https://maven.apache.org/download.cgi).

## Setup Guide

### 1. Clone the Repository

Clone the repository to your local machine.

```bash
git clone 

```

## 2. Build the Project

Navigate to the project root directory and run the following Maven command to build the project:

```bash
mvn clean install
```

This will download the necessary dependencies and compile the project.

### 3. Run the Application

After the build is successful, you can run the application using the following command:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

### 4. Test the Endpoints

**Create Training Center**: You can use Postman or any API testing tool to send a POST request to:

```bash
POST http://localhost:8080/courses/save
```

Example JSON payload for creating a new training center:

```json

{
  "centerName": "ABC Training Center",
  "centerCode": "ABC123XYZ789",
  "address": {
    "detailedAddress": "123, Main Street",
    "city": "New Delhi",
    "state": "Delhi",
    "pincode": "110001"
  },
  "studentCap": 200,
  "coursesOffered": ["Java", "Python", "Spring Boot"],
  "contactEmail": "contact@abc-training.com",
  "contactPhone": "9876543210"
}
    
```

The API will validate the CenterName, Email, and Phone fields. If everything is correct, it will create a new training center and return it in the response.

**Get All Training Centers:**

```bash
GET http://localhost:8080/courses/list
```

This will return a list of all training centers stored .

**Fetch Based On Id**

```bash
GET http://localhost:8080/courses/findById?id=2
```

This will return training centers having id as 2.

**Delete All Training Centers:**

```bash
DELETE http://localhost:8080/courses/deleteAll
```

This will return a list of all training centers stored .

**Delete Based On Id**

```bash
DELETE http://localhost:8080/courses/deleteById?id=1
```

This will delete training center having id as 2.
