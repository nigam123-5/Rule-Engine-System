#  Rule Engine System

A full-stack Rule Engine system that dynamically evaluates user inputs based on configurable business rules. Built using **Spring Boot**, **MySQL**, **React**, and **JWT authentication**.


## Features

-  **Dynamic Rule Management**: Add, update, and delete rules and conditions from the admin dashboard.
- **DAG-Based Rule Evaluation**: Handles complex logic with dependency resolution.
-  **Secure Authentication**: Role-based access using **JWT**.
-  **Admin Dashboard**: UI to manage rules, view evaluations, and assign roles.
-  **Real-time Rule Testing**: Evaluate input sets and view results live.
-  **RESTful APIs**: Backend services for all major functionalities.


##  Tech Stack

**Frontend:**
- React.js
- Axios
-  CSS / Bootstrap

**Backend:**
- Spring Boot
- MySQL
- Spring Security
- JWT (JSON Web Token)

How It Works

1. Admin creates **rules** made up of multiple **conditions**.
2. Rules are stored in a **DAG** (Directed Acyclic Graph) structure to maintain dependency.
3. When a user submits data, it is matched against the stored rules.
4. System processes input → parses through DAG → returns evaluation result.
5. All operations are protected using JWT and role-based access.


