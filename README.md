## Ksu Audit Fund Accounting System

## Technologies
* Gradle 7.5.1 version [Download Gradle](https://gradle.org/next-steps/?version=7.5.1&format=bin)
* Java 17 version

## Setup
* Set Gradle in setting in Intellij Idea -> File | Settings | Build, Execution, Deployment | Build Tools | Gradle
* Set JDK in project settings
* Build project
* Execute scripts in sql files

![image](https://user-images.githubusercontent.com/60696068/205739196-ab80ce19-0a17-439e-91cc-f90ac81b4250.png)

* Optionally, change port, db and url settings
```yaml
springdoc:
  swagger-ui:
    path: /ksu-timetable-api.html
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema="public"
    username: postgres
    password: root
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
server:
  port: 8082
```  

* Set timetable url and uri
```yaml
timetable:
  url: "https://ksu.edu.ru/timetable/2022_2/"
  uri: "timetable.php"
```
* Run project and go to [Swagger](http://localhost:8082/ksu-timetable-api.html)
