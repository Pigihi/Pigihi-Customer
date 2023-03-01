# Customer Service

Service for handling all customer related operations

### Required External Services

- Authentication-Authorization Service

### Depends on

- MongoDB instance

## APIs

| Functionality | REST Endpoint | Parameters | Body | Response |
| --- | --- | --- | --- | --- |
| Get Customer Information | **GET** `/user/customer` | email - String |     | JSON string |
| Add Customer | **POST** `/user/customer` |     | JSON string | JSON string |
| Edit Customer | **PUT** `/user/customer` |     | JSON string | JSON string |
| Disable Customer | **DELETE** `/user/customer` |     | JSON string | JSON string |
| Enable Customer | **PATCH** `/user/customer` |     | JSON string | JSON string |

## Configuration

Edit the properties of **application.yml** file

```yaml
# Eureka properties
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: address of the eureka server (Eg: http://localhost:8761/eureka)
  instance:
    hostname: specify the hostname here (Eg: localhost)

# Server properties
server:
  port: port in which the customer service is to run (Eg: 8091)

# Application properties
spring:
  application:
    name: name of the application (Eg: CUSTOMER-SERVICE)
# MongoDB properties
  data:
    mongodb:
      database: mongoDB database name (Eg: testWorkingDB)
      host: name of mongoDB host (Eg: localhost)
      port: port in which mongoDB is being run (Eg: 27017)
```

## Local Deployment

Service Registry should be started for successful execution of all queries.

In application.yml file, change the properties

| Property | Value | Example |
| --- | --- | --- |
| eureka_hostname | hostname of eureka server | service-registry |
| mongodb_hostname | hostname of mongodb | customer-db |
| mongodb\_database\_name | database name | customerDB |

### Using Docker

Create docker bridge network: `docker network create -d bridge pigihi-network`

docker-compose can be used to run the application and the corresponding mongodb instance

1.  Go to project folder
2.  Open terminal and run `docker-compose up`
3.  The application can be accessed at localhost:8091 (port 8091 is set in docker-compose)
4.  MongoDB port is set to 27016

To run only the application

1.  Go to project folder
2.  Open terminal and run `docker build .`
3.  Run `docker run -p 8091:8091 docker_image_name`
4.  The application can be accessed at localhost:8091

### Using Gradle

MongoDB should be run seperately and the configurations should be updated in application.yml

1.  Go to project folder
2.  Open terminal and run `./gradlew build`
3.  Run `./gradlew bootRun`

* * *
