# Job Search Management System

## Overview

This is a Java-based Job Search Management System developed as a team project.

The system allows users to search for job opportunities, apply for jobs, and manage their application history. Administrators can manage job listings and monitor applications.

## Features

### User Features
- User registration
- User login
- View all available jobs
- Search jobs by keyword
- Apply for job opportunities
- View application history
- Prevent duplicate applications

### Administrator Features
- Administrator login
- Add new job listings
- Update job information
- Delete job listings
- View all job listings
- Search job information

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- File Handling (CSV)
- Java Scanner
- LocalDate (Date Handling)

## Data Storage

The application uses CSV files to store data.

- `users.csv` – User information
- `jobs.csv` – Job information
- `applications.csv` – Job application records

## Project Structure

```text
job_search_system/
├── src/
│   └── teamwork02/
│       ├── Application.java
│       ├── ApplicationDatabase.java
│       ├── Job.java
│       ├── JobDatabase.java
│       ├── JobManagementSystem.java
│       ├── User.java
│       └── UserDatabase.java
├── jobs.csv
├── users.csv
└── applications.csv
```

## How to Run

1. Clone this repository.
2. Open the project in Eclipse IDE or another Java IDE.
3. Make sure the CSV files are in the project working directory.
4. Run `JobManagementSystem.java`.
5. Follow the console menu to use the system.

## What I Learned

Through this project, I gained practical experience in:

- Applying Object-Oriented Programming concepts.
- Designing classes and methods.
- Managing data using ArrayList.
- Reading and writing CSV files.
- Implementing user registration and login functions.
- Developing job search and application management features.
- Working with a team to develop a Java application.

## Project Type

Team Development Project

**Language:** Java
