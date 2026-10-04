# Student Management System

A console-based Student Management System built using Java, JDBC, MySQL, and JUnit 5. The application follows a layered architecture with separate model, DAO, service, and database connection layers.

## Features

- Add student
- View all students
- Search student by ID
- Update student details
- Delete student
- Input validation
- MySQL database persistence
- JDBC-based database operations
- DAO pattern
- Service layer
- JUnit 5 integration tests

## Tech Stack

- Java 21
- JDBC
- MySQL
- Maven
- JUnit 5
- Git

## Project Architecture

```text
Main
 │
 ▼
StudentService
 │
 ▼
StudentDAO
 │
 ▼
StudentDAOImpl
 │
 ▼
DBConnection
 │
 ▼
MySQL