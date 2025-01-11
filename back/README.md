![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

# Quick Start BE

### Build Spring Boot Project with Maven
To be able to run your Spring Boot app, you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a [Maven](https://maven.apache.org/), use the below command. You will need to run it from the project folder containing the pom.xml file.
```shell
maven package
```
or you can also use
```shell
mvn install
```

### Run Spring boot app with java -jar command
To run your Spring Boot app from a command line in a Terminal window, you can use the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.
```shell
java -jar target/web-api-0.0.1-SNAPSHOT.jar
```

### Run Spring boot app using Maven
You can also use the Maven plugin to run your Spring Boot app. Use the below example to run your Spring Boot app with the Maven plugin:
```shell
mvn spring-boot:run
```

---

# Environment installation procedure

## Install MySQL

First install [MySQL](https://www.mysql.com/fr/) on your environment

### 1. Create the user
In a MySQL terminal create a user `user` with a password `123456`.
> You can create your own user with a different username and password. Just don't forget to change selected user in the `application.properties`.
```mysql
CREATE USER 'user'@'localhost' IDENTIFIED BY '123456';
```
Also grant some privileges to your new MySQL user.
```mysql
GRANT SELECT, INSERT, UPDATE, DELETE ON your_car_your_way_db.* TO 'user'@'localhost';
```

### 2. Create the database
Still in a MySQL terminal create a new database named: `your_car_your_way_db` and use it.
````mysql
CREATE DATABASE your_car_your_way_db;
````
````mysql
USE your_car_your_way_db;
````
Once the database created generate them by executing `../ressources/sql/script.sql` script that you can find in `../resources/sql` :
```mysql
source ./script.sql;
```

## Java SDK

The project run in Java JDK 21. You can download [here](https://www.oracle.com/fr/java/technologies/downloads/#jdk21) and install it.

# Project structure

| Folder        | Utility                                   |
|:--------------|:------------------------------------------|
| controllers   | API controllers.                          |
| models        | Model to communicate with the DB.         |
| services      | Processing of received data.              |
| repositories  | Database request access.                  |
| dtos          | Data Transfere Object (DTO).              |
| configurations| All spring project configurations         |
| mappers       | Object Mapper between DTOs and Entities   |


---

## Documentations

- [Swagger](http://localhost:3001/swagger-ui.html) : http://localhost:3001/swagger-ui.html
- [OpenAPI](http://localhost:3001/api-docs) : http://localhost:3001/api-docs
---

## Licensing

This project was built under the MIT licence.
