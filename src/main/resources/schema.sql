CREATE DATABASE IF NOT EXISTS restaurant_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;
USE restaurant_db;

-- Drop tables if they exist to allow re-creation
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS users;

-- Users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT,
    address VARCHAR(255),
    email VARCHAR(255)
);

-- Dishes table
CREATE TABLE dish (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Ingredients table (linked to dish)
CREATE TABLE ingredient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dish_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_ingredient_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
);

-- Orders table
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Order details (line items)
CREATE TABLE order_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    dish_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order_detail_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_detail_dish FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
);

-- Seed data: 5 users
INSERT INTO users (first_name, last_name, age, address, email) VALUES
('Alice', 'Johnson', 28, '123 Maple St', 'alice@example.com'),
('Bob', 'Smith', 35, '456 Oak Ave', 'bob@example.com'),
('Carol', 'Davis', 42, '789 Pine Rd', 'carol@example.com'),
('David', 'Miller', 31, '12 Birch Blvd', 'david@example.com'),
('Eve', 'Wilson', 24, '34 Cedar Ln', 'eve@example.com');

-- Seed data: 5 dishes
INSERT INTO dish (name, price) VALUES
('Margherita Pizza', 9.99),
('Spaghetti Bolognese', 12.50),
('Caesar Salad', 7.75),
('Grilled Salmon', 15.25),
('Beef Burger', 10.50);

-- Seed data: 5 ingredients (mapped to dish ids 1..5)
INSERT INTO ingredient (dish_id, name) VALUES
(1, 'Tomato'),
(1, 'Mozzarella'),
(2, 'Ground Beef'),
(3, 'Romaine Lettuce'),
(5, 'Cheddar');

-- Seed data: 5 orders (user ids 1..5)
INSERT INTO orders (user_id, total_price) VALUES
(1, 19.98),
(2, 12.50),
(3, 25.00),
(4, 15.25),
(5, 10.50);

-- Seed data: 5 order line items (reference existing orders/dishes)
INSERT INTO order_detail (order_id, dish_id, quantity, unit_price) VALUES
(1, 1, 2, 9.99),
(2, 2, 1, 12.50),
(3, 4, 1, 15.25),
(3, 3, 1, 9.75),
(4, 5, 1, 10.50);