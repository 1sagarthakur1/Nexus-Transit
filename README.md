# Nexus Transit

<p align="center">
    <img src="Image/Logo.png" />
<!--     <img src="https://user-images.githubusercontent.com/107456964/220406389-20f8b8d8-ac28-4c2a-b5ed-95f6e1d9afa0.jpg"> -->
</p>  

# Nexus Transit

 **Description:**
Nexus Transit is a Spring Boot-based REST API project designed to facilitate the booking of vehicles for transporting luggage from one location to another. This project allows users to search for available vehicles, book a vehicle based on their requirements, and manage their bookings through the API.

**Key Features:**
- User Management: Handles user authentication for secure access.
- Vehicle Booking: Allows users to search for available vehicles, select a vehicle, and book it for transporting luggage.
- Booking Management: Provides endpoints for users to view, modify, or cancel their bookings.
- Vehicle Slot Management: Manages the availability and status of vehicles.
- Error Handling: Custom error responses and exception handling for a seamless user experience.

## Technologies Used:
- Spring Boot: Framework for building the RESTful API.
- JPA/Hibernate: For database interaction and ORM (Object-Relational Mapping).
- MySQL: Database for storing user, vehicle, and booking information.
- JWT: For handling authentication.
- Swagger/OpenAPI: For API documentation and testing.
<!-- - SWAGGER UI -->

## Dependencies
- SPRING DATA JPA
- SPRING BOOT DEVTOOLS
- SPRING WEB
- HIBERNATE
- MYSQL DRIVER
- VALIDATION
- LOMBOK
- JWT

## Main Modules
- User Module
- Transporter_User Module
- Vehicle Module
- Vehicle Slot Module
- Luggage Module 
- Payment Module
- Delivery Module
- Exception Handler Module

## System Architecture: Nexus Transit

**1. Controller Layer:**

***Controllers:***
Handles incoming HTTP requests and routes them to the appropriate service methods.

**Endpoints:**
- `/user`: Manages user-related operations.
- `/transporters_User`: Manages transporter user operations.
- `/vehicles`: Manages vehicle information and availability.
- `/vehicleslots`: Manages vehicle slot bookings and availability.
- `/luggage`: Manages luggage details and associated booking.
- `/payments`: Handles payment transactions and records.
- `/deliveries`: Manages delivery status and tracking.

**Example:** `UserController`, `VehicleController`, `PaymentController`.

**2. Repository Layer:**

***Repositories:***
Provides database access by extending Spring Data JPA repositories for CRUD operations on each module.

**Repositories:**
- `UserRepository`: Manages `User` entity operations.
- `TransporterUserRepository`: Manages `Transporter_User` entity operations.
- `VehicleRepository`: Manages `Vehicle` entity operations.
- `VehicleSlotRepository`: Manages `VehicleSlot` entity operations.
- `LuggageRepository`: Manages `Luggage` entity operations.
- `PaymentRepository`: Manages `Payment` entity operations.
- `DeliveryRepository`: Manages `Delivery` entity operations.

**Technologies:** Spring Data JPA.

**3. Models Layer:**

***Entities/Models:***
Represents the core business objects in the system.

**Entities:**
- `User`: Stores user information and credentials.
- `Transporter_User`: Stores transporter-specific user information.
- `Vehicle`: Stores vehicle details like type, capacity, and availability.
- `VehicleSlot`: Stores details of vehicle availability slots for booking.
- `Luggage`: Stores information about the luggage being transported.
- `Payment`: Stores payment details for bookings.
- `Delivery`: Stores information about the delivery process and status.

**4. Service Layer:**

***Services:***
Contains business logic and interacts with repositories to perform operations.

**Services:**
- `UserService`: Handles operations related to user management.
- `TransporterUserService`: Manages transporter user operations.
- `VehicleService`: Manages vehicle-related operations and availability.
- `VehicleSlotService`: Manages booking slots and slot availability.
- `LuggageService`: Handles operations related to luggage management.
- `PaymentService`: Manages payment processing and transaction records.
- `DeliveryService`: Handles delivery tracking and updates.

**Technologies:** Spring Service, Transaction Management.

**5. Exception Handler:**

***Global Exception Handler:***
Manages application-wide exception handling, providing consistent error responses.

**Components:**
- `@ControllerAdvice`: Centralized error handling across all controllers.
- Custom exception classes (e.g., `UserException`, `Luggage`).
- Custom error response model.

**6. Security Configuration:**

***JWT Configuration:***
Configures JWT-based authentication for securing the REST API.

**Components:**
- `JwtTokenProvider`: Generates and validates JWT tokens.
- `JwtAuthenticationFilter`: Filters incoming requests and validates JWT tokens.
- `SecurityConfig`: Configures security settings, including protected routes and authentication entry points.

**Technologies:** Spring Security, JWT (JSON Web Token).

**Components:**
- `application.yml` or `application.properties`: Configuration for database, security, and other settings.
- **JWT Configurations:** Secret keys, token validity period, etc.

**Technologies:** Spring Boot Configuration Properties.



## Installation
<!--- copy this https://github.com/sunnylalwani41/Your_Door_Food_REST_API.git -->
- Select the path where you want to store the project on your pc
- open the corresponding file/folder with the editor
- open the terminal of your editor
- use  --> git clone (paste link) <-- 
- after the project is cloned to your folder
- go to the Nexus-Transit file inside com folder
- run as Spring Boot

# FLOW CHART

<img src="Image/flow chart.png" />
<!-- ![FlowChartYourDoorFood](https://user-images.githubusercontent.com/107456964/220468355-2e96143c-3811-433b-ae7a-a7f97d00fa53.jpg) -->

# Entity Relationship Diagram
- https://dbdiagram.io/d/Transpot-Daigram-66a204068b4bb5230e4f45a7
