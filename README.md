To access the Swagger documentation of the API, follow these steps:

Download the project.
Run it in an IDE of your choice.
Open your browser and navigate to the following URL:
The API is fully documented via Swagger.

http://localhost:8080/swagger-ui/index.html

API Security
The API is protected by Spring Security (JWT token). Here are the steps to gain access:

User Registration: Register a user in the database (you can do this via Postman or Swagger).
Login: With the registered user, perform a login.
Token Retrieval: Retrieve the token returned upon login.
Authorize Swagger: Use the token to authorize Swagger. Once authorized, all methods will be accessible.

About the API:
- This REST API is designed for managing medications in a hospital pharmacy. It supports the following functionalities:

- Register Medications: Add new medications to the system.

- Register System Users: Add new users to the system.

- Manage Medication Quantities: Add or remove quantities of medications.

- Deletion: Perform direct deletion or logical deletion based on business rules.

- Security Control: Requests are allowed only after logging in and including the token for authentication.

Password Encryption: Passwords are encrypted using Bcrypt to ensure the security of the institution and its functions.

Technologies Used:

- Java
- Spring Boot
- JWT Token
- MySQL
- Swagger
- Spring Security
- Bcrypt
