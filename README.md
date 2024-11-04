# Venue Management System - Backend

Welcome to the backend repository for the **Venue Management System**, a comprehensive web application designed to streamline venue booking, event scheduling, and secure communication between organizers and clients.

## Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features
The Venue Management System offers a range of features to enhance user experience and operational efficiency:

- **User Management**: Register, log in, and manage user profiles.
- **Venue Management**: Create, update, and delete venues with detailed information such as location, capacity, and amenities.
- **Event Scheduling**: Schedule events, assign venues, and manage bookings with conflict resolution.
- **Address Management**: Manage venue addresses, including building name, campus, room area, and nearby landmarks.
- **Secure Communication**: Facilitate secure messaging between organizers and clients to discuss event details.

## Technology Stack
The backend is built using a robust technology stack that ensures performance and scalability:

- **Java 17**: Modern programming language for building the application.
- **Spring Boot**: Framework for REST API development.
- **Hibernate/JPA**: Object Relational Mapping (ORM) tool for database interactions.
- **MySQL**: Relational database management system for data storage.
- **Maven**: Build automation tool for dependency management.
- **Lombok**: Library to reduce boilerplate code in Java classes.

## Project Structure
```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── venue
│   │           ├── controller     # API Controllers handling requests
│   │           ├── dto            # Data Transfer Objects for request/response bodies
│   │           ├── entity         # Entity Models representing database tables
│   │           ├── mapper         # Mapper Classes for converting between DTOs and entities
│   │           ├── repository     # JPA Repositories for data access layer
│   │           └── service        # Service Layer containing business logic
│   └── resources
│       ├── application.properties # Configuration Properties (DB settings, etc.)
│       └── data.sql               # Sample Data for testing purposes
└── test                           # Unit and Integration Tests
