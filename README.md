# Spring Boot Expense Tracker API

A RESTful backend API for managing personal expenses using Spring Boot, JPA and MySQL

## Overview

This project manages personal expenses and provides aggregated insights such as totals by 
category and date range. It is a backend API that can be integrated with a frontend to 
make it more user-friendly. It follows a layered architecture using Entity, Repository,
Service, and REST Controller layers. This project was built as a self-learning backend
development exercise.

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

## Tools
- IntelliJ IDEA
- MySQL Workbench
- Postman
- Git & GitHub

## Architecture

Entity → Repository → Service → REST Controller

## Features

### CRUD
- Create Expense
- Get All Expenses
- Get Expense By ID
- Update Expense
- Delete Expense

### Category
- List By Category, sorted by Date DESC, id DESC
- Total By Category (JPQL SUM)
- Category Normalization (trim + lowercase)

### Aggregations
- Total of all Expenses
- Total by Custom Date Range

### Date Range
- List by Custom Date Range
- Result sorted by date DESC, id DESC
- Default Handling: 
  - end = today if null
  - start = end - 30 days if null
- Validation: Start cannot be after End

## Default Behaviours
- Date defaults to today if null
- Category is trimmed and lowercased before save/query
- Null SUM returns 0.0
- Results are sorted by date DESC, id DESC wherever applicable

## API Endpoints
```
POST /api/expenses
GET /api/expenses
GET /api/expenses/{id}
PUT /api/expenses
DELETE /api/expenses/{id}

GET /api/expenses/category/{category}
GET /api/expenses/range?start=yyyy-MM-dd&end=yyyy-MM-dd

GET /api/expenses/total/{category}
GET /api/expenses/total
GET /api/expenses/range/total/?start=yyyy-MM-dd&end=yyyy-MM-dd
```

## Database
The database is managed using MySQL.
The Schema Script can be found in /database/create-schema.sql

## Future Improvements
- Current month total
- Last 30 days total endpoint 
- Global exception handling
- Validation annotations
- DTO layer
- BigDecimal instead of Double
- Proper HTTP status codes

## How to Run
1. Configure MySQL credentials in application.properties
2. Run Schema Script
3. Run the Spring Boot Application
