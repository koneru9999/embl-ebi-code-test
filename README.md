#### Technologies / Frameworks Used
* Spring Boot 2.0.4
    * Spring Web MVC
    * Spring Security (with simple in-memory authentication)
    * HTTP Basic Authentication
    * Spring asynchronous events for in-process pub-sub mechanism
    * Spring Data JPA
    * Hibernate
* JSR-303 Bean Validation
* PostgreSQL 
* Lombok
* MapStruct
* Swagger2
* JUnit 5
* H2 (integration test)

#### Pre-requisites
* JDK 8 
* Maven 3.3 and above
* Docker with docker-compose

#### Build
Clone the git repo to your local using `git clone https://bitbucket.org/koneru9999/embl-ebi-code-test.git`.

Navigate to root folder of the cloned repo using cmd (windows) or terminal (MacOS) and issue `mvn clean install`.

This command will compile, test, and package a spring boot uber jar (with all dependencies).


#### Running the application
First build the application (refer to above section). Once the application has successfully built, 

* Start the PostgreSQL using `docker-compose up -d db`
* Once the database container is up and running, Start the application container using `docker-compose up person-service`


#### REST Services
Swagger can be accessed using [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

A simple Authentication has been enabled with the following list of users.

| User / Password   | ROLES     |   Granted Authorities |
|-------------------|:---------:|:---------------------:|
| user / password   | USER      |    -                  |
| user2 / password  | USER      | READ_PRIVILEGES       |
| manager / password| MANAGER   | READ_PRIVILEGES,      | 
|                   |           | WRITE_PRIVILEGES      |

Authorities required for REST services:

| REST Service              |   Authorities required    |
|:--------------------------|:-------------------------:|
| **PUT** /person           | WRITE_PRIVILEGES          |
| **GET** /person           | READ_PRIVILEGES           |
| **POST** /person          | WRITE_PRIVILEGES          |
| **GET** /person/{id}      | READ_PRIVILEGES           |
| **DELETE** /person/{id}   | WRITE_PRIVILEGES          |

> NOTE: Please use browser in a private mode with swagger UI. Otherwise, 
> Swagger will use previous authentication details for subsequent calls.

Alternatively, Any REST client can be used without depending on Swagger UI.