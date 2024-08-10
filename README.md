Download the project.
Execute it in an IDE of your choice.
Open your browser and navigate to the following URL:
http://localhost:8080/swagger-ui/index.html
The API is fully documented via Swagger.

API Security
The API is protected by Spring Security (JWT token). Here are the steps to gain access:

- User Registration: Register a user in the database (you can do this via Postman or Swagger).
- Login: With the user registered, log in.
- Obtain Token: Retrieve the token returned after login.
- Authorize Swagger: Use the token to authorize Swagger. Once authorized, all methods will be accessible.
About the API:

This REST API is designed to manage medications in a hospital pharmacy. It supports the following functionalities:

- Register Medications: Add new medications to the system.
- Register System Users: Add new users to the system.
- Manage Medication Quantities: Add or remove medication quantities.
- Manage kind Medication: ORAL, NASAL, VENOSO, INTRAMUSCULAR, RETAL
- Manage kind Laboratory : MEDLEY, ACHE
- Deletion: Perform direct or logical deletion based on business rules.
- Security Control: Requests are allowed only after login and inclusion of the token for authentication.
- Password Encryption: Passwords are encrypted using Bcrypt to ensure the institution's security and its functions.
  
Technologies Used:

- Java
- Spring Boot
- JWT Token
- MySQL
- Flyway (manage database tables)
- Swagger
- Spring Security
- Bcrypt

Endpoints : 

![Captura de tela 2024-08-10 202406](https://github.com/user-attachments/assets/ba348651-4e13-42ff-8817-fe6236598705)

