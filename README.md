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

## Curl Examples 

- purchase request
``` 
curl -X POST "http://localhost:8080/lcs-service/lcs/v1/earn" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"cashierId\": 1, \"mobileNumber\": \"07962315974\", \"purchaseAmount\": 1000}"
```

- redeem request with redeem points
```
curl -X POST "http://localhost:8080/lcs-service/lcs/v1/redeem" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"cashierId\": 1, \"idCardNumber\": \"test-id-card\", \"mobileNumber\": \"string\", \"pointToRedeem\": 200}"
```

- redeem request with water packets
```
curl -X POST "http://localhost:8080/lcs-service/lcs/v1/redeem" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"cashierId\": 1, \"mobileNumber\": \"07962315974\", \"numberOfWaterPackets\": 1}"
```

- get unClaimedAccounts 

```
curl -X GET "http://localhost:8080/lcs-service/lcs/v1/users/unclaimed-accounts" -H "accept: application/json"
```

