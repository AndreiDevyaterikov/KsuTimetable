## Ksu Audit Fund Accounting System

## Technologies
* Gradle 7.5.1 version [Download Gradle](https://gradle.org/next-steps/?version=7.5.1&format=bin)
* Java 17 version

## Setup
* Set Gradle in setting in Intellij Idea -> File | Settings | Build, Execution, Deployment | Build Tools | Gradle
* Set JDK in project settings
* Build project

* Optionally, change port, db and url settings
```yaml
springdoc:
  swagger-ui:
    path:
spring:
  datasource:
    url:
    username:
    password:
  jpa:
    database-platform:
server:
  port:
```  

* Set timetable url and uri
```yaml
timetable:
  url: ""
  uri: ""
```
* Run project and go to Swagger
