E-Commerce Microservices

    This repository contains several microservices for an e-commerce application that I've worked on during my internship. The services include Order Service, User Service, Product Service, and Inventory Service. Each service is developed using Spring Boot 3+ and Java 
    21. The final services I will work on are Email Service and Notification Service, which will integrate with Kafka.



Project Overview
This project represents a modular approach to building an e-commerce platform. Each microservice focuses on a specific domain and is independently deployable and scalable. The communication between these services is primarily REST-based, and future services will leverage Kafka for event-driven architecture.

Technologies Used:-


    Java 21

    Spring Boot 3+

    PostgreSQL for persistent storage (except Product Service which uses MongoDB)

    Flyway for database migrations

    Spring Cloud Sleuth for distributed tracing

    Spring Security for authentication and authorization

    Kafka for message-driven architecture (forthcoming in the Email and Notification Services)

    Swagger/OpenAPI for API documentation

    Testcontainers for running integration tests in isolated environments




Microservices Overview:-



  1) User Service

      The User Service is responsible for managing user accounts and authentication. It supports functionalities such as user registration, login, and account management.

            Technology: Spring Boot, PostgreSQL, Flyway (database migrations)

         Key Features:

            User registration and authentication

            JWT-based security for API calls

            OpenAPI integration for API documentation

         pom.xml Dependencies:

            spring-boot-starter-data-jpa

            spring-boot-starter-web

            spring-boot-starter-security

            flyway-core

            jjwt-api (for JWT support)

2) Product Service

   The Product Service manages the product catalog. This service stores information about products, categories, and their details.

         Technology: Spring Boot, MongoDB

       Key Features:

              CRUD operations for products

              OpenAPI integration for API documentation

       pom.xml Dependencies:

               spring-boot-starter-data-mongodb

               spring-boot-starter-web

                spring-boot-starter-security

                springdoc-openapi-starter-webmvc-ui

3) Order Service

   The Order Service is responsible for processing customer orders. It handles order placement, order status tracking, and interaction with inventory and payment services.

       Technology: Spring Boot, PostgreSQL, Kafka (for future event-driven communication)

       Key Features:

            Order creation and status updates

            Integration with user and inventory services

       pom.xml Dependencies:

            spring-boot-starter-data-jpa

            spring-boot-starter-web

           spring-boot-starter-security

           spring-cloud-starter-openfeign

           spring-kafka (future integration)

4) Inventory Service
 
  The Inventory Service manages product inventory levels and stock availability. It interacts with the Order Service to update inventory when orders are placed.

    Technology: Spring Boot, PostgreSQL, Flyway (database migrations)

    Key Features:

         Inventory tracking

         Real-time updates on stock levels

         OpenAPI integration for API documentation

    pom.xml Dependencies:

         spring-boot-starter-data-jpa

         spring-boot-starter-web

         spring-boot-starter-security

         flyway-core

         springdoc-openapi-starter-webmvc-ui


  Kafka (future services):

      Make sure Kafka is running if you're working with services like Email Service and Notification Service that will be integrated with Kafka. You can use Docker to spin up a local Kafka instance.


Configuration and Dependencies

Database Configuration

Each service that interacts with a database uses Flyway for schema management. You'll need to configure the following in the application.properties for each service:

PostgreSQL:

properties:-

       spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
       spring.datasource.username=yourusername
       spring.datasource.password=yourpassword
       spring.flyway.enabled=true
       MongoDB (for Product Service):

properties:- 

    spring.data.mongodb.uri=mongodb://localhost:27017/yourdbname
    Messaging (Kafka) Configuration (Upcoming)
    
In the future, services like Email Service and Notification Service will use Kafka for asynchronous communication. The configuration will be added to application.properties:

properties

       spring.kafka.bootstrap-servers=localhost:9092
       
Conclusion:- 

This repository showcases my work on building scalable microservices for an e-commerce platform. The architecture follows best practices in microservice design, with a focus on modularity, scalability, and security. Future improvements will involve enhancing the event-driven architecture with Kafka, along with implementing the Email Service and Notification Service.

If you have any questions or suggestions, feel free to reach out!  

Contact :-  amolyenge.sde2025@gmail.com
