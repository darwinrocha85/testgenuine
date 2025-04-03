# Spring Boot 3 CRUD API

## Description

This is a simple CRUD (Create, Read, Update, Delete) API developed with Spring Boot 3. It uses an H2 in-memory database and follows the MVC pattern with **Services, Controllers, and Repositories**. The project manages `Client` entities stored in the `clients` table.

## Technologies Used

- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Spring Web**

## Project Structure

```bash
├── src/main/java/com/example/crud
│   ├── controller
│   │   ├── ClientController.java
│   ├── model
│   │   ├── Client.java
│   ├── repository
│   │   ├── ClientRepository.java
│   ├── service
│   │   ├── ClientService.java
│   │   ├── ClientServiceImpl.java
│   ├── CrudApplication.java
│
├── src/main/resources
│   ├── application.properties
```

## Entity Model: `Client`

```java
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "address", nullable = false, length = 1000)
    private String address;

    @Column(name = "phone", nullable = false)
    private long phone;

    @Column(name = "new_employee")
    private boolean newEmployee;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "data", nullable = true, length = 10000)
    private String data;
}
```

## Endpoints

```bash
GET    /clients       # Get all clients
GET    /clients/{id}  # Get client by ID
POST   /clients       # Create new client
PUT    /clients/{id}  # Update client
DELETE /clients/{id}  # Delete client
```

## 🚀 Running the Project

```bash
# Clone the repository
git clone <repository-url>
cd <repository-folder>

# Ensure Java 17+ and Maven are installed

# Run the project
mvn spring-boot:run

# Access the API at:
http://localhost:8080/clients
```

