# LibraryManagementServices

This project is designed for user to issue books and keep track of past orders just like a virtual library.

## The project is divided into two parts:
### 1. LibraryManagementServices
  - All the micro-services running in the backend are included under this repository which we are currently present right now. 
### 2. LibraryManagementApp
  - The angular app which talks with backend is included under this module.

## Microservice Architecture Diagram
<img src="https://user-images.githubusercontent.com/57723556/159715307-eeabc929-efe1-46ce-8b20-347c38a451ec.JPG" width="800" height="590">

## Eureka Server
- Eureka Server is an application that holds the information about all client-service applications. 
- Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address.

<img src="https://user-images.githubusercontent.com/57723556/159727409-cecdd2e2-cd9b-486d-8e4c-92ef996e4003.JPG" width="900" height="300">

## API Gateway
- The API Gateway Service is a Spring Boot application that routes client requests to the Message service.
- The Gateway intercepts all requests from clients. It then routes the requests to the appropriate microservice.

## Cart Service
- The Cart Service is used to manage the user's cart which user interacts with to add, remove and checkout books.
<img src="https://user-images.githubusercontent.com/57723556/159729142-ef114ff3-a51f-49bd-b2b3-dd89f3cc7a2d.JPG" width="900" height="300">

## Order Service
- The order Service is used to get all books ordered by user, process order during checkout and delete order when book is returned.
<img src="https://user-images.githubusercontent.com/57723556/159730940-56e3d68d-8861-4d5a-be6b-b873abf93d2b.JPG" width="900" height="200">

## Authorization Service
- The Authorization Service is used to create user, edit user and other user related functionalities.
- Also this service is used to generate JWT tokens which are then later used for validating each request from user. 
<img src="https://user-images.githubusercontent.com/57723556/159731893-f1043264-f066-4172-b9a9-40acc71e9e81.JPG" width="900" height="400">

## Book Service
- The Book Service is used to get all books in inventory.
- The service also has functionalites which are used by other microservices to increase and decrease the book quantity as user orders the book.
- Also we can get book according to the filter applied.
<img src="https://user-images.githubusercontent.com/57723556/159732598-9c366781-7a19-405f-91ea-0148fe490827.JPG" width="900" height="400">

## Wallet Service
- The Wallet Service is used to manage user wallet for deposits to collect fine as necessary.
- User will also get a invoice generated when fine is being paid.
<img src="https://user-images.githubusercontent.com/57723556/159733479-f38e5823-0514-4850-bf1c-501c2cb2618a.JPG" width="900" height="400">
