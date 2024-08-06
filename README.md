# Nexus Transit

<p align="center">
    <img src="Images/logoYourDoorFood.png" />
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


## System Structure
The REST API allows a User to **Signup, Login** & **Browse** through the food, view products as well as the Restaurant and add food to **Cart** wishlist, as well as for the Restaurant to **View** all the **Orders, Customers & food**.
Restaurants can **Add, Update, View, and Delete** **Food**  from the **Database**.
Administrator can **View request Delete Account List** and **Delete the Account As Per Request**.

## Installation

<!--- copy this https://github.com/sunnylalwani41/Your_Door_Food_REST_API.git -->
- Select the path where you want to store the project on your pc
- open the corresponding file/folder with the editor
- open the terminal of your editor
- use  --> git clone (paste link) <-- 
- after the project is cloned to your folder
- go to the YourDoorFoodApplication.java file inside com folder
- run as Spring Boot

# FLOW CHART

<img src="Images/Flow_Chart.jpg" />
<!-- ![FlowChartYourDoorFood](https://user-images.githubusercontent.com/107456964/220468355-2e96143c-3811-433b-ae7a-a7f97d00fa53.jpg) -->

# Entity Relationship Diagram

<img src="Images/Your Door Food Entity Relationship Diagram.jpg" />
