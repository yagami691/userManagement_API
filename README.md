# User Authentication API

This API allows managing user authentication and basic user data operations such as creating, updating, deleting, and retrieving users.
This  is also documented on Swagger with this url `http://localhost:8080/swagger-ui.html
`

## Prerequisites

Before running this project, ensure you have the following installed:

- **Java 17+**
- **Maven 3.8+**
- **Spring Boot 3+**
- **Postgresql Server or any compatible database**
- **Postman or cURL** (for testing the endpoints)

Make sure to configure your `application.properties` or `application.yml` file with appropriate database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create
```

## Running the Project

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. The application will be available at `http://localhost:8080`.

## Endpoints

### 1. Get All Users
**Request:**  
`GET http://localhost:8080/auth/allUsers`

**Description:**  
Fetches a list of all registered users.

---

### 2. Add a New User
**Request:**  
`POST http://localhost:8080/auth/addUsers`  
**Headers:**  
`Content-Type: application/json`  
**Body:**
```json
{
  "name": "mos",
  "email": "spring@gmail.com",
  "password": "mosthinker"
}
```

**Description:**  
Creates a new user with the provided information.

---

### 3. Update a User
**Request:**  
`PUT http://localhost:8080/auth/7`  
**Headers:**  
`Content-Type: application/json`  
**Body:**
```json
{
  "name": "Fatimatou Merveille",
  "email": "fatimatou@gmail.com",
  "password": "Fat_@lemonde"
}
```

**Description:**  
Updates the user with ID `7` using the provided information.

---

### 4. Delete a User
**Request:**  
`DELETE http://localhost:8080/auth/7`

**Description:**  
Deletes the user with ID `7`.

---

### 5. User Login
**Request:**  
`POST http://localhost:8080/auth/login`  
**Headers:**  
`Content-Type: application/json`  
**Body:**
```json
{
  "email": "fonkeurider@ymail.com",
  "password": "FonBoy_#Cameroon2025"
}
```

**Description:**  
Authenticates a user with the given email and password which generates a token

---

## Author

Created by **Nkounga Moïse**
