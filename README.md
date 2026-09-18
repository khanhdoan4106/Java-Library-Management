# Library Management System

A console-based **Java application** for managing books, users, and borrowing records — built to practice core **Object-Oriented Programming (OOP)** principles.

## Overview

This project simulates a small library system where users can borrow and return books, while the system tracks availability, due dates, and overdue records. It is not intended as a production application, but as a hands-on exercise in class design, inheritance, and interfaces in Java.

## Features

- Add and remove books
- Search books by title
- Display all books and their availability
- Add users and view their currently borrowed books
- Borrow and return books
- Track and report overdue books
- Support multiple book types with distinct borrowing rules:
  - **Printed Book** — limited physical copies, 14-day loan period
  - **E-book** — unlimited copies, 7-day loan period

## Project Structure

```text
LibraryManagement/
├── src/
│   └── main/
│       ├── Main.java
│       ├── model/
│       │   ├── Book.java
│       │   ├── PrintedBook.java
│       │   ├── Ebook.java
│       │   ├── Borrowable.java
│       │   ├── User.java
│       │   └── BorrowRecord.java
│       └── service/
│           └── Library.java
├── README.md
└── .gitignore
```

## Technologies

- Java 17+
- Java Collections (`List`, `ArrayList`)
- `java.time.LocalDate`
- Java Stream API
- Command-line interface (`Scanner`)

## Getting Started

### Requirements

- JDK 17 or later
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) — optional

### Compile and run from the command line

```bash
javac -d out $(find src -name "*.java")
java -cp out main.Main
```

### Or run from an IDE

Open the project and run `src/main/Main.java` directly.

## Usage

On launch, the program presents a menu-driven interface:

```text
===== LIBRARY MANAGEMENT =====
1. Add book
2. Remove book
3. Search book
4. Show all books
5. Add user
6. Show users
7. Borrow book
8. Return book
9. Show overdue books
0. Exit
```

## Learning Goals

This project was built to practice:

- Class and object design
- Encapsulation, inheritance, abstraction, and polymorphism
- Interface design and the Interface Segregation Principle
- Modeling relationships between objects (composition)
- Organizing a project into `model`/`service` layers
- Working with Java Collections and the Stream API

## Possible Improvements

- Track quantity for printed books with multiple copies
- Enforce a per-user borrowing limit
- Calculate fines for overdue returns
- Persist data to a file or database
- Add a graphical interface
- Add unit tests

