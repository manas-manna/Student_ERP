
# **Student Billing Management System**

A robust backend system for managing student billing and payment processing, with seamless integration for admin and student functionalities.

---

## **Table of Contents**

- [Project Overview](#project-overview)  
- [Features](#features)  
- [API Endpoints](#api-endpoints)  
  - [Admin APIs](#admin-apis)  
  - [Student APIs](#student-apis)  
  - [Billing and Payments APIs](#billing-and-payments-apis)  
- [Setup Instructions](#setup-instructions)  
  - [Backend](#backend)  
  - [Frontend](#frontend)  
- [Postman Collection](#postman-collection)  

---

## **Project Overview**

The **Student Billing Management System** simplifies the billing and payment workflows for students. It includes APIs for creating and managing student and admin users, generating bills, and processing payments.

---

## **Features**

- Admin and Student account creation.  
- User login (Admin/Student).  
- View all students and their billing information.  
- Generate and fetch bills.  
- Process and view payment history.  

---

## **API Endpoints**

### **Admin APIs**

- **POST** `/api/v1/admins`  
  Create a new admin account.  

  **Request Body (JSON):**
  ```json
  {
    "email": "manas@gmail.com",
    "firstName": "Manas",
    "lastName": "Manna",
    "password": "pass1234"
  }
  ```

- **GET** `/api/v1/auth/admin`  
  Admin login.

---

### **Student APIs**

- **POST** `/api/v1/students`  
  Create a new student account.  

  **Request Body (JSON):**
  ```json
  {
    "rollNumber": "MT2024167",
    "firstName": "Aashish",
    "lastName": "Vaswani",
    "email": "Vaswani.Aashish@iiitb.ac.in",
    "password": "pass1234"
  }
  ```

- **GET** `/api/v1/auth/student`  
  Student login.  

- **GET** `/api/v1/students`  
  Fetch all students.  

- **GET** `/api/v1/students/bills`  
  Fetch all bills of a specific student.  
  **Headers:**  
  ```json
  {
    "studentId": "<student_id>"
  }
  ```

---

### **Billing and Payments APIs**

- **POST** `/api/v1/bills`  
  Create a bill.

- **POST** `/api/v1/payments`  
  Process a payment.  

- **GET** `/api/v1/payments`  
  Fetch payment history of a specific student.  
  **Headers:**  
  ```json
  {
    "studentId": "<student_id>"
  }
  ```

---

## **Setup Instructions**

### **Backend**

1. Navigate to the `backend` folder.  
2. Open the project in IntelliJ IDEA.  
3. Customize the `application.properties` file to match your database configuration.  
4. Run the project.  
5. The server will be available at:  
   `http://localhost:8080`

### **Frontend**

1. Navigate to the `frontend` folder.  
2. Open the project in Visual Studio Code (VSCode).  
3. In the terminal, run:  
   ```bash
   npm start
   ```
4. The frontend will run at the default React port.

---

## **Postman Collection**

1. Use [Postman](https://www.postman.com/) to test the APIs.  
2. Configure the following endpoints in Postman:  
   - **Admin Creation**: `POST /api/v1/admins`  
   - **Student Creation**: `POST /api/v1/students`  
   - **Login**: `GET /api/v1/auth/student` or `GET /api/v1/auth/admin`  
   - Additional endpoints listed above.  
3. Ensure to pass appropriate headers for endpoints requiring `studentId`.  

---

Enjoy building with the **Student Billing Management System**! 🚀
