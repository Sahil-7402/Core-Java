-- -----------------------------------------
-- 1. Create Database
-- -----------------------------------------
CREATE DATABASE IF NOT EXISTS restaurantdb;
USE restaurantdb;

-- -----------------------------------------
-- 2. Drop existing tables (if any)
-- -----------------------------------------
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS restaurants;

-- -----------------------------------------
-- 3. Create 'restaurants' Table
-- -----------------------------------------
CREATE TABLE restaurants (
    id INT PRIMARY KEY,
    restaurant_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);

-- -----------------------------------------
-- 4. Insert Sample Restaurants
-- -----------------------------------------
INSERT INTO restaurants VALUES
(1, 'FoodHub', 'foodhub@gmail.com', 'pass123', 'Pune'),
(2, 'SpiceVilla', 'spicevilla@gmail.com', 'spice123', 'Mumbai');

-- -----------------------------------------
-- 5. Create 'food' Table with 'description'
-- -----------------------------------------
CREATE TABLE food (
    id INT PRIMARY KEY,
    food_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DOUBLE NOT NULL,
    category ENUM('VEG','NON_VEG','BEVERAGE','DESSERT','STARTER','MAIN_COURSE') NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

-- -----------------------------------------
-- 6. Insert Sample Foods
-- -----------------------------------------
INSERT INTO food VALUES
(101, 'Paneer Butter Masala', 'Creamy North Indian dish', 250.00, 'VEG', TRUE),
(102, 'Chicken Biryani', 'Spicy Hyderabadi Biryani', 300.00, 'NON_VEG', TRUE),
(103, 'Coke', 'Chilled Soft Drink', 60.00, 'BEVERAGE', TRUE),
(104, 'Chocolate Cake', 'Rich dessert with cocoa', 150.00, 'DESSERT', TRUE),
(105, 'Spring Rolls', 'Crispy Veg Starter', 120.00, 'STARTER', TRUE),
(106, 'Dal Tadka', 'Main Course Lentil Dish', 180.00, 'MAIN_COURSE', TRUE),
(107, 'Old Soup', 'Out of stock item', 90.00, 'STARTER', FALSE);

-- -----------------------------------------
-- 7. Stored Procedure: Get foods below 500
-- -----------------------------------------
DELIMITER $$

CREATE PROCEDURE get_food_below_500()
BEGIN
    SELECT * FROM food WHERE price < 500;
END$$

DELIMITER ;

-- -----------------------------------------
-- 8. Execute Stored Procedure
-- -----------------------------------------
CALL get_food_below_500();
