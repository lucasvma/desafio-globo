# Challange Backend Globo

>Develop SpringBoot Application which register and consult users.

### Solution
Based on the requirements of this project, we note that:
- It doesn't have to be a robust architecture to solve the problem.

### Tools and Technologies

Following the requirements, we will work with the following tools and technologies:
- Java8+
- MongoDB
- Maven
- JUnit
- Mockito

### Installation
To install and run the project you want to execute the following commands:
```bash
#Clone the project
$ git clone https://github.com/lucasvma/desafio-globo
$ cd desafio-globo

#Build Project
$ mvn clean install

#Run the application
$ mvn spring-boot:run

#Run unit tests
$ mvn test
```

### Swagger UI
You can use swagger-ui to test the project:
http://localhost:8080/swagger-ui.html

### Routes

#### User registration:
- http://localhost:8080/user (POST)
    - {
      "birthday": "21/07/1998",
      "cep": "12630-000",
      "document": "474-534-598-42",
      "name": "Lucas Ventura"
      }

#### Consult registered user:
* http://localhost:8080/user/{document} (GET)

