🍽️ Restaurant Management System — JDBC + DAO

A clean Java project demonstrating JDBC, DAO design pattern, OOP, and file handling.
This mini-project allows you to manage restaurants and food menus using a MySQL database.

🚀 Features
Restaurant Operations

Add restaurant

Delete restaurant

Food Operations

Add food

Find food by category

Update price (only if available)

View foods priced below ₹500

Delete all unavailable food items

File Handling

Serialize a list of Food objects

Deserialize and read foods from file (foods.dat)

🛠️ Tech Stack

Java (Core Java + OOP)

JDBC (MySQL Connector/J)

DAO Pattern

Serialization

FileInputStream / FileOutputStream

MySQL

restaurant-management/
│
├── src/com/sunbeam/
│   ├── Category.java
│   ├── DBUtil.java
│   ├── Food.java
│   ├── Restaurant.java
│   ├── RestaurantDao.java
│   ├── RestaurantDaoImpl.java
│   └── Tester.java
│
├── sql/
│   └── tables.sql
│
└── README.md
