# Getting Started
# File Sharing Application
This is a file sharing application that allows users to upload, download,versioning and share files. It also includes user authentication using JWT (JSON Web Tokens).
## Features
- File Upload: Users can upload files to the server.
- File Download: Users can download files from the server.
- File Sharing: Users can share files with other users.
- File Versioning: Users can view and download previous versions of files.
- User Authentication: Users are authenticated using JWT.

## Technologies & tools used
- Java 17
- Spring Boot 3.3.1
- Spring Security
- JWT
- MySQL
- Hibernate
- Maven
- Postman
- IntelliJ 
- Flyway

## Dependency

– or MySQL:
```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties

- For MySQL
```
spring.datasource.url=jdbc:mysql://localhost:3306/testdb_spring?useSSL=false
spring.datasource.username=root(put your username)
spring.datasource.password=123456(put your password)

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

# App Properties
filesharingapp.app.jwtSecret= ======================sonali=Spring===========================(put your secret key)
filesharingapp.app.jwtExpirationMs=86400000(put your expiration time)
```
## Dependencies used
- Spring Web
- Spring Security
- MySQL Driver
- Flyway Migration
- Lombok
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Boot Starter Validation
- jwt (io.jsonwebtoken)
- Spring Boot Starter Data JPA
- Spring Boot Configuration Processor
```
## Run Spring Boot application
```
mvn spring-boot:run
```
## Run following SQL insert statements

- INSERT INTO roles(name) VALUES('ROLE_USER');
- INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
- INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

## API Endpoints
- signup: POST localhost:8080/api/auth/signup
```
{
  "username": "sha",
  "email": "sha4201@gmail.com",
    "role":["user"],
  "password": "sha@123"
  
}
``` 
- signin: POST localhost:8080/api/auth/signin
```
{
  "username": "Sha",
  "password": "Sha@123"
}
```
## Once you sign in, you will get a token. You need to pass this token in the header for the following requests from Authentication tab of postman by selecting Bearer Token.
- upload file: POST localhost:8080/files/upload
- in Body select form-data and key as file and as value upload file 
```
{
  "file": uploaded file
}
```
- download file: GET localhost:8080/files/{fileId}
- share file: POST localhost:8080/share
```
{
"fileDbId":1,
"senderId":1,
"receiverId":2,
"date":"2024-06-22"

}
```
- For versioning
- update file: PUT localhost:8080/files/{fileId}


### Reference Documentation
For further reference, please consider the following sections:

* [Git hub link](https://github.com/sonali-2507/FileSharingApp.git)


