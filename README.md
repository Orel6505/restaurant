# README.md

# Restaurant Management System

This is a Spring Boot application for managing a restaurant's dishes, ingredients, orders, and users. It provides a RESTful API for performing CRUD operations on these entities.

## Features

- Manage dishes: Create, read, update, and delete dishes.
- Manage ingredients: Create, read, update, and delete ingredients associated with dishes.
- Manage orders: Create, read, update, and delete orders made by users.
- Manage users: Create, read, update, and delete user profiles.

## Technologies Used

- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL (or any other relational database)
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- A relational database (e.g., PostgreSQL)

### Installation

1. Clone the repository:

   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:

   ```
   cd restaurant
   ```

3. Update the `application.properties` file in `src/main/resources` with your database connection settings.

4. Build the project using Maven:

   ```
   mvn clean install
   ```

5. Run the application:

   ```
   mvn spring-boot:run
   ```

### API Endpoints

- **Dishes**
  - `GET /api/dishes` - Retrieve all dishes
  - `GET /api/dishes/{id}` - Retrieve a dish by ID
  - `GET /api/dishes/search?name={name}` - Search dishes by name
  - `POST /api/dishes` - Create a new dish
  - `PUT /api/dishes/{id}` - Update an existing dish
  - `DELETE /api/dishes/{id}` - Delete a dish

- **Ingredients**
  - `GET /api/ingredients` - Retrieve all ingredients
  - `GET /api/ingredients/{id}` - Retrieve an ingredient by ID
  - `POST /api/ingredients` - Create a new ingredient
  - `PUT /api/ingredients/{id}` - Update an existing ingredient
  - `DELETE /api/ingredients/{id}` - Delete an ingredient

- **Orders**
  - `GET /api/orders` - Retrieve all orders
  - `GET /api/orders/{id}` - Retrieve an order by ID
  - `GET /api/orders/{id}/full` - Retrieve complete order with all details
  - `GET /api/orders/price-range?minPrice={min}&maxPrice={max}` - Find orders within a price range
  - `POST /api/orders` - Create a new order
  - `PUT /api/orders/{id}` - Update an existing order
  - `DELETE /api/orders/{id}` - Delete an order

- **Users**
  - `GET /api/users` - Retrieve all users
  - `GET /api/users/{id}` - Retrieve a user by ID
  - `POST /api/users` - Create a new user
  - `PUT /api/users/{id}` - Update an existing user
  - `DELETE /api/users/{id}` - Delete a user

### Analytics & JPQL Queries

The application includes advanced JPQL queries for analytics:

- **Total spent by user**: Calculate total amount spent by a specific user across all orders
- **Popular dishes**: Get dishes ranked by order frequency (most ordered first)
- **Orders by price range**: Filter orders within minimum and maximum price values
- **Average order value**: Calculate average order amount for a specific user
- **User statistics**: Get combined statistics (order count and total spent) for a user

## Database Schema

The database schema is defined in the `schema.sql` file located in `src/main/resources`. It includes tables for users, orders, order details, dishes, and ingredients.