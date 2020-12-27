### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

### Instructions
- Access the application by following credentials
Username - admin
Password - password

### Implementation
Implemented the following in the existing code.
- Created a DTO layer and used map struct for mapping.
- Used EhCache for caching database calls.
- Implemented Spring security basic authentication to protect endpoints.
- Created a schema.sql and data.sql file to populate the h2 database.
- Implemented Integration test and Service Layer Unit test cases.
- Used Mockito and JUnit for test cases.
- Added detailed messages to the Swagger API UI.

### Would have implemented following if more time permitted
1) UI based Rest API.
2) Advanced authentication by Spring Boot.
3) Implement test cases for all Classes like Controller, Entity etc
4)Database like MySQL instead of in memory database

#### My experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have around 7 years of experience in web application development.
- 3 years of experience in PHP and 4 years in Java
- Experience coding in Spring, Spring MVC
- In progress of learning Spring Boot and its features.
