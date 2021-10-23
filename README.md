# Supermarket_Loyalty_Card_System

this is a maven-multi-module solution for task provided by RS2.

# Getting Started

clone the repository run maven target clean install and then start the application.

This solution have two profiles local and server (those profile usually used for deployment purposes), for testing
purposes you can use local profile by providing and environment variable called:

spring.profiles.active = local

# Technology Stack

- Java 1.8
- H2 Database
- Maven
- Spring boot

# H2-Console

to view data base schema please use: http://localhost:8080/lcs-service/h2-console.

## API Document URL

swagger:
http://localhost:8080/lcs-service/swagger-ui.html#/
