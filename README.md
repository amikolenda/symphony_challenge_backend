# Symphony Olympic Games


## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[H2](https://https://www.h2database.com/) - Relational Database Management System
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.

## External Tools Used

* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* 	[Postman Echo](https://docs.postman-echo.com/?version=latest) - A service that can be used to test your REST clients and make sample API calls. It provides endpoints for GET, POST, PUT, various auth mechanisms and other utility endpoints.


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `is.symphony.collegeinternship.olympicgames.OlympicGamesApplication.java` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Database configuration

* 	In its default configuration, aplication uses an in-memory database (H2) which gets populated at startup with data. The h2 console is automatically exposed at http://localhost:8080/h2-console and it is possible to inspect the content of the database using the jdbc:h2:mem:test url. User name is: sa

### Security

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

~~Default username is `admin` and password is `admin`~~


### URLs

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/signup`                           | GET | Sign up for volunteers |
|`http://localhost:8080/login`                       | GET | Log in for athletes, volunteers or admins |
|`http://localhost:8080/api/upload`                 | POST | Upload for json file containing athletes data  |
|`http://localhost:8080/api/countries` | GET | List of all countries |


### URLs - Athlete

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/athletes` | GET | List of all athletes |
|`http://localhost:8080/api/athletes/{id}`                             | GET | All informations about an athlete|
|`http://localhost:8080/api/athletes/{id}` | PUT | Update an athlete |
|`http://localhost:8080/api/athletes/{id}` | DELETE | Delete an athlete |

### URLs - Volunteer

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/volunteers` | GET | List of all volunteers |
|`http://localhost:8080/api/volunteers/{id}`  | GET | All informations about a volunteer|
|`http://localhost:8080/api/volunteers/{id}` | PUT | Update a volunteer |
|`http://localhost:8080/api/volunteers/{id}` | DELETE | Delete a volunteer |

### URLs - Sport

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/sports` | GET | List of all sports |
|`http://localhost:8080/api/sports` | POST | Create a sport |
|`http://localhost:8080/api/sports/{id}` | GET | All information about a sport |
|`http://localhost:8080/api/sports/{id}` | PUT | Update a sport |
|`http://localhost:8080/api/sports/{id}` | DELETE | Delete a sport |

### URLs - Competition

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/competitions/all/{state}` | GET | List of all competitions by their states |
|`http://localhost:8080/api/competitions` | POST | Create a competition |
|`http://localhost:8080/api/competitions/{id}` | GET | All information about a competition |
|`http://localhost:8080/api/competitions/{id}` | PUT | Update a competition |
|`http://localhost:8080/api/competitions/{id}` | DELETE | Delete a competition |

### URLs - Start Competition - Ski Jumping

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/competitions/start` | POST | Create a jump |
|`http://localhost:8080/api/competitions/start/jump` | GET | List of all jumps |
|`http://localhost:8080/api/competitions/start/{id}` | GET | All information about a jump |
|`http://localhost:8080/api/competitions/start/{id}` | PUT | Update a jump |
|`http://localhost:8080/api/competitions/start/{id}` | DELETE | Delete a jump |
|`http://localhost:8080/api/competitions/results` | GET | List of live results |


### URLs - End Competition - Final Results

|  URL |  Method | Description |
|----------|--------------|--------------|
|`http://localhost:8080/api/competitions/end` | GET | List of final results |
|`http://localhost:8080/api/competitions/end/{id}` | GET | All information about a final result |


## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```text
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── is.symphony.collegeinternship.olympicgames
│           ├── is.symphony.collegeinternship.olympicgames.configurations
│           ├── is.symphony.collegeinternship.olympicgames.controllers
│           ├── is.symphony.collegeinternship.olympicgames.application.exceptions
│           ├── is.symphony.collegeinternship.olympicgames.application.models
│           ├── is.symphony.collegeinternship.olympicgames.application.repositories
│           ├── is.symphony.collegeinternship.olympicgames.security
│           ├── is.symphony.collegeinternship.olympicgames.services
│           └── is.symphony.collegeinternship.olympicgames.utils
├── src
│   └── main
│       └── resources
│           └── json
│           │   ├── athletes.json
│           │   ├── countries.json
│           ├── application.properties
├── src
│   └── test
│       └── java
│           ├── is.symphony.collegeinternship.olympicgames.controllers
│           └── is.symphony.collegeinternship.olympicgames.services.impl
├── JRE System Library
├── Maven Dependencies
├── src
├── target
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## packages

* 	`models` — to hold our entities;
* 	`repositories` — to communicate with the database;
* 	`services` — to hold our business logic;
* 	`security` — security configuration;
* 	`controllers` — to listen to the client;

* 	`resources/` - Contains files to populate database.
* 	`resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

* 	`test/` - contains unit and integration tests

* 	`pom.xml` - contains all the project dependencies


