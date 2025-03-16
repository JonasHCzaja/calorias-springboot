# Calorias - Backend in Spring Boot

## 📊 About the Project

Calorias is a system for managing food calories, allowing users to register foods and track their nutritional intake. The goal is to facilitate food tracking, helping users maintain a balanced diet.

## 🚀 Features

- User registration and secure authentication via JWT
- Full CRUD management for food items
- Role-based access control (ADMIN, USER)
- Integration with a PostgreSQL database
- Secure authentication and session management
- Support for execution in a Docker container

## 🛠️ Technologies Used

- **Java 17** as the main programming language
- **Spring Boot 3.x** for backend development
- **Spring Security** for JWT authentication
- **Hibernate/JPA** for data persistence
- **PostgreSQL** as the relational database
- **Maven** for dependency management
- **Docker** for containerization and deployment

## 🎯 Project Structure

```
calorias-springboot/
├── src/main/java/br/com/fiap/calorias/
│   ├── controller/  # REST Controllers (Food, Users, Authentication)
│   ├── dto/         # Data Transfer Objects
│   ├── config/      # Security configurations (JWT, SecurityConfig)
│   ├── service/     # Business logic layer
│   ├── repository/  # Persistence layer (Spring Data JPA)
│   └── model/       # Database entities
├── src/main/resources/
│   ├── application.properties  # Database configuration
│   ├── db/migration/           # Flyway scripts for database versioning
├── pom.xml          # Maven dependencies
├── Dockerfile       # Containerization setup
└── README.md        # Documentation
```

## ⚡ How to Run the Project

### Prerequisites

- **JDK 17**
- **Maven 3+**
- **Docker (Optional, to run PostgreSQL)**

### Steps

1. Clone the repository:

    ```sh
    git clone https://github.com/your-user/calorias-springboot.git
    cd calorias-springboot
    ```

2. Set up the PostgreSQL database (if not using Docker, edit `application.properties`):

    ```sh
    docker run --name calorias-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
    ```

3. Compile and run the project:

    ```sh
    ./mvnw spring-boot:run
    ```

## 🔑 JWT Authentication

The system uses **JSON Web Token (JWT)** authentication. To access protected endpoints:

1. Log in at `/api/auth/login` with a registered user and obtain a token.
2. Use the token in the request header:

    ```sh
    Authorization: Bearer <your-jwt-token>
    ```

## 📌 Main Endpoints

| Method | Endpoint          | Description                        | Authentication Required |
|--------|------------------|------------------------------------|-------------------------|
| POST   | `/api/auth/login` | JWT authentication                | ❌ No                   |
| GET    | `/api/alimentos`  | Lists all food items              | ✅ Yes                  |
| POST   | `/api/alimentos`  | Registers a new food item         | ✅ Yes                  |
| GET    | `/api/usuarios`   | Lists users (ADMIN only)          | ✅ Yes (ADMIN)          |

## 🎓 Academic Context

This project was developed as part of the **Analysis and Systems Development** course at **FIAP**, with the objective of applying concepts studied throughout the discipline. The development covered the following topics:

- **Java Spring Boot** for API development
- **Maven** for dependency management
- **Hibernate** for data persistence
- **Docker** for containerization and deployment

#

Developed by **JonasHCzaja**.
