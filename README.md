### Play Spring-Boot + Hibernet Web application

#### Build Spring boot web applications supporting CRUD operations through form-based UI built on thymeleaf and spring MVC support.
https://howtodoinjava.com/spring-boot2/crud-application-thymeleaf/

#### Configure H2 database with Spring boot
https://howtodoinjava.com/spring-boot2/h2-database-example

#### To utilize H2-database writing data into file use the next property in application.properties:
spring.datasource.url = jdbc:h2:file:C:/dev/H2/data/m11
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#### To utilize H2-database temporary writing data into memory:
spring.datasource.url=jdbc:h2:mem:m11 ... and so on...

